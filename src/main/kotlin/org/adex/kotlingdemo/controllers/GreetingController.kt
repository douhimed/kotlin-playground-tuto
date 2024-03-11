package org.adex.kotlingdemo.controllers

import org.adex.kotlingdemo.services.GreetingServices
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class GreetingController(val greetingServices: GreetingServices) {

    @GetMapping("{name}")
    fun sayHello(@PathVariable name: String): ResponseEntity<String> {
        return ResponseEntity.ok(greetingServices.getGreeting(name))
    }

}