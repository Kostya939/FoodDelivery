package com.fooddelivery.controller;

import com.fooddelivery.model.Order;
import com.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public String placeOrder(
            @RequestParam Long restaurantId,
            @RequestParam List<Long> dishIds,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String address,
            Model model) {

        try {
            Order order = orderService.createOrder(restaurantId, dishIds, name, phone, address);
            model.addAttribute("order", order);
            return "orderSuccess";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        try {
            List<Order> orders = orderService.getAllOrders();
            model.addAttribute("orders", orders);
            return "orders";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/order/{id}")
    public String viewOrderById(@PathVariable Long id, Model model) {
        try {
            Order order = orderService.getOrderById(id);
            if (order != null) {
                model.addAttribute("order", order);
                return "order";
            } else {
                model.addAttribute("errorMessage", "Order not found");
                return "error";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }
}
