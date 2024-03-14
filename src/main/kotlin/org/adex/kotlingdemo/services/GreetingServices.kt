package org.adex.kotlingdemo.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class GreetingServices {

    @Value("\${app.message}")
    lateinit var message : String

    fun getGreeting(name: String) : String{
        return "$message ${name.uppercase(Locale.getDefault())}"
    }
}