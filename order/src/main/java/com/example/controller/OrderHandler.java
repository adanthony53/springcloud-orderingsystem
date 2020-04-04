package com.example.controller;

import com.example.entity.Order;
import com.example.entity.OrderVO;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/findByUid/{uid}/{index}/{limit}")
    public OrderVO findByUid(@PathVariable("uid") long uid,
                             @PathVariable("index") int index,
                             @PathVariable("limit") int limit) {
        return new OrderVO(0, "msg", orderRepository.countByUid(uid), orderRepository.findByUid(uid, index, limit));
    }

    @GetMapping("/findAll/{index}/{limit}")
    public OrderVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return new OrderVO(0, "msg", orderRepository.count(), orderRepository.findAll(index, limit));
    }

    @PostMapping("/save")
    public void save(@RequestBody Order order) {
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @PutMapping("/updateState/{aid}/{id}")
    public void updateState(@PathVariable("aid") long aid, @PathVariable("id") long id) {
        orderRepository.updateState(aid, id);
    }
}
