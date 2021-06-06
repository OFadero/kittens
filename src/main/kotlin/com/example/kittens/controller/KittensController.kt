package com.example.kittens.controller

import com.example.kittens.service.DirectionService
import com.example.kittens.service.LocationService
import com.example.kittens.utils.EmailValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/api")
class KittensController(val directionService: DirectionService,
 val locationService: LocationService) {

    @GetMapping("/{email}/directions")
    fun fetchDirections(@PathVariable email: String): ResponseEntity<Any> {
        if(!EmailValidator.isEmailValid(email)) return  ResponseEntity("forbidden",HttpStatus.FORBIDDEN)

        return ResponseEntity.ok(directionService.findAll())
    }


    @GetMapping("{email}/location/{x}/{y}")
    fun findOutIfFound(@PathVariable email: String, @PathVariable x: String,
                        @PathVariable y: String): ResponseEntity<Any> {

        if(!EmailValidator.isEmailValid(email)) return  ResponseEntity("forbidden",HttpStatus.FORBIDDEN)

        return if(locationService.findLatest(x.toLong(), y.toLong())) {
            ResponseEntity( "success", HttpStatus.OK)
        } else {
            ResponseEntity("not found", HttpStatus.NOT_FOUND)
        }
    }

}