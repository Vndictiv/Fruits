package com.example.demo.service;

import com.example.demo.model.Fruit;
import com.example.demo.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceImp implements FruitService{

    private FruitRepository fruitRepository;

    @Autowired
    public FruitServiceImp(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public List<Fruit> findAll() {
        return fruitRepository.findAll();
    }

    @Override
    public void save(Fruit fruit) {
        fruitRepository.save(fruit);

    }

    @Override
    public List<Fruit> findFruitsByNameStartsWith(String s) {
        List<Fruit> fruits = fruitRepository.findAll();


        return fruits.stream().filter(f -> f.getName().toLowerCase().startsWith(s.toLowerCase())).collect(Collectors.toList());

    }
}
