package org.adex.kotlingdemo.controllers

import mu.KLogging
import org.adex.kotlingdemo.services.impl.GreetingServices
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class GreetingController(val greetingServices: GreetingServices) {

    companion object : KLogging()

    @GetMapping("{name}")
    fun sayHello(@PathVariable name: String): ResponseEntity<String> {
        logger.info("Endpoint : GET /greeting/{name}")
        return ResponseEntity.ok(greetingServices.getGreeting(name))
    }

}