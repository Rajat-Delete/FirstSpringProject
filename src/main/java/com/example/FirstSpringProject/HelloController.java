package com.example.FirstSpringProject;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

import java.io.IOException;

@RestController
public class HelloController {


    @GetMapping("/hello")
    public String getHello(){
        return "<b>Hey from SpringBoot!!</b>";
    }


    @GetMapping("/")
    public String getHomePage(){
        return "Welcome to HomePage...";
    }

    @GetMapping(path = "/test/{id}")
    public String getTestPage(@PathVariable(value = "id") String id) throws IOException {
        System.out.println(":id"+id);
        //let's try to do one API call and return the data in the same response
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fakestoreapi.com/").addConverterFactory(GsonConverterFactory.create()).build();

        ProductService productService = retrofit.create(ProductService.class);
        Product p = productService.getProduct(id).execute().body();
        System.out.println("Data is p ::  "+p);

        return p.toString();
    }
}
