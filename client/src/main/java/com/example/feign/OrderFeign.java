package com.example.feign;

import com.example.entity.MenuVO;
import com.example.entity.Order;
import com.example.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "order")
public interface OrderFeign {

    @GetMapping("/order/findByUid/{uid}/{index}/{limit}")
    public OrderVO findByUid(@PathVariable("uid") long uid,
                           @PathVariable("index") int index,
                           @PathVariable("limit") int limit);

    @GetMapping("/order/findAll/{index}/{limit}")
    public OrderVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @PostMapping("/order/save")
    public void save(@RequestBody Order order);

    @PutMapping("/order/updateState/{aid}/{id}")
    public void updateState(@PathVariable("aid") long aid, @PathVariable("id") long id);
}
