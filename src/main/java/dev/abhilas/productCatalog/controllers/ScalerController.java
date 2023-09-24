package dev.abhilas.productCatalog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScalerController {

    @GetMapping("/hi")
    public String hiEveryone() {
        return "Hi Scalerite Students";
    }
}
