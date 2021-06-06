package com.example.kittens.repository

import com.example.kittens.model.Direction
import com.example.kittens.model.Location
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository



@Repository
interface DirectionRepository: CrudRepository<Direction, Long>

interface LocationRepository: CrudRepository<Location, Long>