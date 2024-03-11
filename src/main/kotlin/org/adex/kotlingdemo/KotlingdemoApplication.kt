package org.adex.kotlingdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlingdemoApplication

fun main(args: Array<String>) {
	runApplication<KotlingdemoApplication>(*args)
}
