package br.com.inventorycontrol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class HelloWorld {

    @GetMapping("/hello")
    public ResponseEntity<String>  helloWorld(){
        return ResponseEntity.ok().body("Hello World");
    }
}
