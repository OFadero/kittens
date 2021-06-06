package com.example.kittens.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "DIRECTION")
data class Direction(
    val directionDate: LocalDateTime,
    val directionString: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val directionId: Long? = null
)