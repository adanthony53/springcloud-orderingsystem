package com.example.controller;

import com.example.entity.UserVO;
import com.example.repository.UserRepository;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

//    @Value("${server.port}")
//    private String port;
//
//    @GetMapping("/index")
//    public String index() {
//        return "current port: " + this.port;
//    }

    @GetMapping("/findAll/{index}/{limit}")
    public UserVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return new UserVO(0, "msg", userRepository.count(), userRepository.findAll(index, limit));
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") int id) {
        return userRepository.findById(id);
    }

    @GetMapping("/count")
    public int count() {
        return userRepository.count();
    }

    @PostMapping("/save")
    public void save(@RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user) {
        userRepository.update(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id) {
        userRepository.deleteById(id);
    }
}
