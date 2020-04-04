package com.example.controller;

import com.example.entity.*;
import com.example.feign.MenuFeign;
import com.example.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/order", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;

    //@Autowired
    //private MenuFeign menuFeign;

    @GetMapping("/findByUid")
    @ResponseBody
    public OrderVO findByUid(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session) {
        long uid = ((User) session.getAttribute("user")).getId();
        int index = (page - 1) * limit;
        return orderFeign.findByUid(uid, index, limit);
    }

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session) {
        Order order = new Order();
        order.setUser((User) session.getAttribute("user"));
        //order.setMenu(menuFeign.findById((long) mid));

        Menu menu = new Menu();
        menu.setId((long) mid);
        order.setMenu(menu);

        orderFeign.save(order);
        return "index";
    }


}
