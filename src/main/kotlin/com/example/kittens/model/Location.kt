package com.example.kittens.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Location")
data class Location(
    val xLocation: Long,
    val yLocation: Long,
    val isActive: Boolean,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val locationId: Long? = null
)