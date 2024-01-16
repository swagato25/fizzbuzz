package com.roche.fizzbuzz.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FizzBuzzControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFizzBuzz() throws Exception {
        mockMvc.perform(get("/fizzbuzz")
                        .param("int1", "3")
                        .param("int2", "5")
                        .param("limit", "15")
                        .param("str1", "fizz")
                        .param("str2", "buzz"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"1\",\"2\",\"fizz\",\"4\",\"buzz\",\"fizz\",\"7\",\"8\",\"fizz\",\"buzz\",\"11\",\"fizz\",\"13\",\"14\",\"fizzbuzz\"]"));
    }
}
