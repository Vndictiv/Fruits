package com.example.demo.controller;

import com.example.demo.model.Fruit;
import com.example.demo.service.FruitService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.security.auth.message.AuthException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class FruitControllerMyTest {

   @Mock
    private FruitService fruitService;

   @InjectMocks
    private FruitController fruitController;

   private MockMvc mockMvc;

   @Before
    public void setUp(){
       MockitoAnnotations.initMocks(this);

       mockMvc = MockMvcBuilders.standaloneSetup(fruitController).build();
   }


    @Test
    public void getAll() throws Exception {
       List<Fruit> fruits = new ArrayList<>();
       fruits.add(new Fruit());
       fruits.add(new Fruit());


       when(fruitService.findAll()).thenReturn((List) fruits);

       mockMvc.perform(get("/app/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("fruits"))
               .andExpect(model().attribute("fruits", fruits));

    }

    @Test
    public void testSave() throws Exception {
        long id =1;
        String name="Banana";


        Fruit returnFruit = new Fruit();
        returnFruit.setId(id);
        returnFruit.setName(name);


      //  when(fruitService.save(Matchers.<Fruit>any())).thenReturn(returnFruit);
      //  when(fruitService.save(org.mockito.Matchers.any(<Fruit>)).thenReturn(returnFruit);
      //  when(fruitService.save(Mockito.mock(Fruit))).thenReturn(returnFruit);

        mockMvc.perform(post("/app/save")
                .param("id", "1")
                .param("name", name))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/app/list"))
                .andExpect(model().attribute("fruit", instanceOf(Fruit.class)))
                .andExpect(model().attribute("fruit", hasProperty("id",is(id))))
                .andExpect(model().attribute("fruit", hasProperty("name",is(name))));


        ArgumentCaptor<Fruit> boundFruit = ArgumentCaptor.forClass(Fruit.class);
        verify(fruitService).save(boundFruit.capture());

        assertEquals(id, boundFruit.getValue().getId());
        assertEquals(name, boundFruit.getValue().getName());


    }

    @Test
    public void testSaveUser() throws Exception    {

        Fruit newFruit = new Fruit(1,"banana");



    }

    public void testCreateUserWhenSaved()    {
        //Create one sample userDto object with test data
//        when(mockedUserRepository.findOne(userDto.getId())).thenReturn(null);
//        when(mockedUserRepository.findOneByLogin(userDto.getId())).thenReturn(null);

        Fruit fruitt = new Fruit();
        //Create sample User object and set all the properties
        Fruit newFruit = new Fruit();
        newFruit.setId(1);
        newFruit.setName("banana");

//        when(fruitService.save(Mockito.any(Fruit.class)).thenReturn(newFruit);
//        Fruit returnedFruit=fruitService.save(any());
        //You have two ways to test, you can either verify that save method was invoked by
        //verify method
     //   verify(fruitService, times(1)).save(Mockito.any(Fruit.class);
        //or by assertion statements, match the authToken in the returned object to be equal
        //to the one set by you in the mocked object
//        Assert.assertEquals(returnedFruit ,fruitt);
    }

}