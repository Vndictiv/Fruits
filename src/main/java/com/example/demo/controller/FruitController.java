package com.example.demo.controller;


import com.example.demo.model.Fruit;
import com.example.demo.repository.FruitRepository;

import com.example.demo.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class FruitController {


    private FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

//    private FruitRepository fruitRepository;
//
//    public FruitController(FruitRepository fruitRepository) {
//        this.fruitRepository = fruitRepository;
//    }

    @GetMapping("/home")
    public String home(){

        return "index";
    }


    @RequestMapping( value = "/list", method = RequestMethod.GET)
    public String getAll(Model model){

        List<Fruit> fruits = fruitService.findAll();

        model.addAttribute("fruits", fruits);

        return "fruits";
    }

    @GetMapping("/add")
    public String add(){

        return "save-fruits";
    }

    @PostMapping("/save")
    public String saveFruit(@ModelAttribute("fruit") Fruit fruit){

        fruitService.save(fruit);

        return "redirect:/app/list";
    }

    @GetMapping("/stream")
    public String getByChar(@RequestParam("letter") String letter, Model model){

        List<Fruit> fruits = fruitService.findFruitsByNameStartsWith(letter);

        model.addAttribute("fruits", fruits);

        return "stream-method";
    }


}
