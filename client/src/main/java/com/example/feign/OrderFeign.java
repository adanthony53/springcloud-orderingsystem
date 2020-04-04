package com.example.feign;

import com.example.entity.MenuVO;
import com.example.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "order")
public interface OrderFeign {

    @GetMapping("/order/findAll/{index}/{limit}")
    public List<Order> findAll(@PathVariable("index") int index,
                               @PathVariable("limit") int limit);

    @PostMapping("/order/save")
    public void save(@RequestBody Order order);
}
