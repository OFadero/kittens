package com.example.kittens.service

import com.example.kittens.model.Direction
import com.example.kittens.repository.DirectionRepository
import org.springframework.stereotype.Service

@Service
class DirectionService (val directionRepository: DirectionRepository){

    fun findAll(): MutableIterable<Direction> = directionRepository.findAll()


}