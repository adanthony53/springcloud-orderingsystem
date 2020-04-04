package com.example.feign;

import com.example.entity.MenuVO;
import com.example.entity.Order;
import com.example.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "order")
public interface OrderFeign {

    @GetMapping("/order/findByUid/{uid}/{index}/{limit}")
    public OrderVO findByUid(@PathVariable("uid") long uid,
                           @PathVariable("index") int index,
                           @PathVariable("limit") int limit);

    @PostMapping("/order/save")
    public void save(@RequestBody Order order);
}
