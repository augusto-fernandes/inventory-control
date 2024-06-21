package br.com.inventorycontrol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello-world")
public class HelloWorld {

    @GetMapping("/")
    public ResponseEntity<String>  index(){
        String response = "{\"body\": \"Hello World\"}";
        return ResponseEntity.ok(response);
    }
}
