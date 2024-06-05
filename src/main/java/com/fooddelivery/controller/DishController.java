package com.fooddelivery.controller;

import com.fooddelivery.model.Dish;
import com.fooddelivery.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/restaurant/{id}/dishes")
    public String getDishesByRestaurantId(@PathVariable Long id, Model model) {
        List<Dish> dishes = dishService.getDishesByRestaurantId(id);
        model.addAttribute("dishes", dishes);
        model.addAttribute("restaurantId", id);
        return "dishes";
    }
}
