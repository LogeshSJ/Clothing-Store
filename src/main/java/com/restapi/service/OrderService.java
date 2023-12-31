package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDto orderDto;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderedClothRepository orderedClothRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
//    @Autowired
//    private OrderedCloth orderedCloth;

    @Transactional
    public List<OrderResponse> placeOrder(Long userId, Long addressId) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("addressId", "addressId", addressId));

        OrderStatus orderStatus = orderStatusRepository.findById(1L)
                .orElseThrow(() ->
                        new ResourceNotFoundException("orderStatusId", "orderStatusId", 1));

        List<Cart> cartList = (List<Cart>) cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));

        Order order = new Order();
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order.setAppUser(appUser);

        order = orderRepository.save(order);

        for (Cart cart : cartList) {
            OrderedCloth orderedCloth = new OrderedCloth();
            orderedCloth.setOrder(order);
            orderedCloth.setTitle(cart.getCloth().getTitle());
            orderedCloth.setDescription(cart.getCloth().getDescription());
            orderedCloth.setPrice(cart.getCloth().getPrice());
            orderedCloth.setCount(cart.getCount());
            orderedClothRepository.save(orderedCloth);
            cartRepository.delete(cart);
        }

        return getUserOrders(userId);
    }

//    public List<OrderResponse> getUserOrders(Long userId) {
//        List<Order> orderList = (List<Order>) orderRepository.findUserOrder(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));
//        return orderDto.mapToOrderResponse(orderList);
//    }
public List<OrderResponse> getUserOrders(Long userId) {
    List<Order> orderList = orderRepository.findByAppUserId(userId);
    return orderDto.mapToOrderResponse(orderList);
}

    public List<OrderResponse> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    public List<OrderResponse> updateOrderStatus(Long orderId, Long statusId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orderId", "orderId", orderId));

        OrderStatus orderStatus = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", statusId));

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);

        return getAllOrders();
    }
}
