package com.example.demo.service;

import com.example.demo.model.Fruit;
import com.example.demo.repository.FruitRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FruitServiceImpTest {

    @Mock
    FruitRepository fruitRepository;

    @InjectMocks
    FruitServiceImp fruitService;

    @Before
    public void setUp() throws Exception {

       MockitoAnnotations.initMocks(this);

    }

    @Test
    public void findAll() {

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit(1l,"banana"));
        fruits.add(new Fruit(2l,"apple"));

        when(fruitRepository.findAll()).thenReturn(fruits);

        List<Fruit> fruitList = fruitService.findAll();

        assertNotNull(fruitList);
        assertEquals(2, fruitList.size());



    }

    @Test
    public void save() {

        Fruit fruitToReturn = new Fruit(1l,"banana");

        fruitService.save(fruitToReturn);

        verify(fruitRepository).save(any(Fruit.class));
    }

    @Test
    public void findFruitsByNameStartsWith() {

    }
}