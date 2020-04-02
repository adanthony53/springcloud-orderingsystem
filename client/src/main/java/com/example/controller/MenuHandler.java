package com.example.controller;

import com.example.entity.Menu;
import com.example.entity.MenuVO;
import com.example.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/menu", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class MenuHandler {

    @Autowired
    private MenuFeign menuFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public MenuVO findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        return menuFeign.findAll(index, limit);
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id) {
        menuFeign.deleteById(id);
        return "redirect:/menu/redirect/index";
    }

    @GetMapping("/findTypes")
    public ModelAndView findTypes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_add");
        modelAndView.addObject("list", menuFeign.findTypes());
        return modelAndView;
    }

    @GetMapping("/save")
    public String save(Menu menu) {
        //System.out.println("enter step 1");
        menuFeign.save(menu);
        return "redirect:/menu/redirect/index";
    }





    // TODO: other methods for Menu service

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location) {
        return location;
    }

}
