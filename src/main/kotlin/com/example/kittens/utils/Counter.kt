package com.example.kittens.utils

import java.util.concurrent.atomic.AtomicInteger

class Counter {
    private val counter = AtomicInteger(0)
    val value: Int
        get() = counter.get()

    fun increment() {
        while (true) {
            val existingValue = value
            val newValue = existingValue + 1
            if (counter.compareAndSet(existingValue, newValue)) {
                return
            }
        }
    }
}