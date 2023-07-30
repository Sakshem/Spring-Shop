package com.sakshem.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.sakshem.orderservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;

import com.sakshem.orderservice.dto.OrderLineItemsDto;
import com.sakshem.orderservice.dto.OrderRequest;
import com.sakshem.orderservice.model.Order;
import com.sakshem.orderservice.model.OrderLineItems;
import com.sakshem.orderservice.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
            .stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();
        // makes a synchronous call to the inventory-service
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class).block();
        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock());
        if (allProductsInStock) {
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock, please try again later.");
        }
    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems(); 
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
