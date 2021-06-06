package com.example.kittens

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KittensApplication

fun main(args: Array<String>) {
	runApplication<KittensApplication>(*args)
}
