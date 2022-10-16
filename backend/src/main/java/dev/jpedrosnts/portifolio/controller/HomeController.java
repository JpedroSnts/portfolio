package dev.jpedrosnts.portifolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().body("Portifólio JpedroSnts");
    }
}
