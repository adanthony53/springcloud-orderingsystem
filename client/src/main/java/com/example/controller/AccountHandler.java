package com.example.controller;

import com.example.entity.User;
import com.example.entity.Admin;
import com.example.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping(value = "/account", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("type") String type,
                        HttpSession session) {
        Object obj = accountFeign.login(username, password, type);
        LinkedHashMap<String, Object> map = (LinkedHashMap) obj;

        if (obj == null) {  // login failed
            return "login";
        } else if (type.equals("user")) {
            User user = convertUser(map);
            session.setAttribute("user", user);
            return "index";
        } else {
            Admin admin = convertAdmin(map);
            session.setAttribute("admin", admin);
            return "main";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location) {
        return location;
    }

    private User convertUser(LinkedHashMap<String, Object> map) {
        User user = new User();
        user.setId(Long.parseLong(map.get("id").toString()));
        user.setUsername(map.get("username").toString());
        user.setPassword(map.get("password").toString());
        user.setNickname( map.get("nickname").toString());
        user.setGender(map.get("gender").toString());
        user.setTelephone(map.get("telephone").toString());
        //TODO: user.setRegisterdate(map.get("registerdate"));
        user.setAddress(map.get("address").toString());
        return user;
    }

    private Admin convertAdmin(LinkedHashMap<String, Object> map) {
        Admin admin = new Admin();
        admin.setId(Long.parseLong(map.get("id").toString()));
        admin.setUsername(map.get("username").toString());
        admin.setPassword(map.get("password").toString());
        return admin;
    }
}
