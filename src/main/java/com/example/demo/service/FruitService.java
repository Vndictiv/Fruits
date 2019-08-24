package com.example.demo.service;

import com.example.demo.model.Fruit;

import java.util.List;

public interface FruitService {

    List<Fruit> findAll();
    void save(Fruit fruit);
    List<Fruit> findFruitsByNameStartsWith(String s);

}
