package com.example.demo.repository;

import com.example.demo.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    List<Fruit> findFruitsByNameStartsWith(String s);
}
