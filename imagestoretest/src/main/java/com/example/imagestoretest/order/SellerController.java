package com.example.imagestoretest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public String showOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "seller-orders";
    }

    @GetMapping("/history")
    public String showHistory(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "history";
    }
}
