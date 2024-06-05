package com.fooddelivery.service;

import com.fooddelivery.model.Dish;
import com.fooddelivery.model.Order;
import com.fooddelivery.repository.DishRepository;
import com.fooddelivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    public Order createOrder(Long restaurantId, List<Long> dishIds, String name, String phone, String address) {
        try {
            List<Dish> dishes = dishRepository.findAllById(dishIds);
            if (dishes.isEmpty()) {
                throw new IllegalArgumentException("Dish list cannot be empty");
            }

            Order order = new Order();
            order.setRestaurantId(restaurantId);
            order.setDishes(dishes);
            order.setName(name);
            order.setPhone(phone);
            order.setAddress(address);
            order.setStatus("Pending");

            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create order: " + e.getMessage(), e);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }
}
