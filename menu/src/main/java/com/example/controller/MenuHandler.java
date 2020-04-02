package com.example.controller;

import com.example.entity.Menu;
import com.example.entity.MenuVO;
import com.example.entity.Type;
import com.example.repository.MenuRepository;
import com.example.repository.TypeRepository;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class MenuHandler {

    @Value("${server.port}")
    private String port;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/index")
    public String index() {
        return "current port: " + this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        //List<Menu> list = menuRepository.findAll(index, limit);
        return new MenuVO(0, "msg", menuRepository.count(), menuRepository.findAll(index, limit));
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id) {
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes() {
        return typeRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Menu menu) {
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id) {
        return menuRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Menu menu) {
        menuRepository.update(menu);
    }
}
