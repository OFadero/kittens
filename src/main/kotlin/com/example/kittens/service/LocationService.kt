package com.example.kittens.service

import com.example.kittens.model.Location
import com.example.kittens.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService (val locationRepository: LocationRepository){

    fun findLatest(xValue: Long, yValue: Long): Boolean {
       val location = locationRepository.findAll().filter { location -> location.isActive }
        if (xValue == location.last().xLocation && yValue == location.last().yLocation) return true
        return false
    }
}