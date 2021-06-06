package com.example.kittens.service

import com.example.kittens.model.Direction
import com.example.kittens.model.Location
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDateTime
import java.time.Month

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceTest{

    @Test
    fun `findAll Directions`() {

        val directionService = mockk<DirectionService>()

        val dateTime1 = LocalDateTime.of(2021, Month.MAY, 15, 3, 15)
        val dateTime2 = LocalDateTime.of(2021, Month.MAY, 16, 9, 18)
        val directionsList = mutableListOf(
            Direction(dateTime1, "aString",1),
            Direction(dateTime2, "bString",2)
        )

        every {directionService.findAll()} returns directionsList

        val list = directionService.findAll()
        assertThat(list.count()).isEqualTo(2)
        assertThat(list.iterator().next().directionString).isEqualTo("aString")

    }

    @Test
    fun `get an active location`(){

        val x = 9L
        val y = 8L

        val locationList =  mutableListOf(
            Location(10, 5, false),
            Location(26, 4, false),
            Location(9, 8, true)
        )

        val locationService = mockk<LocationService>()
        every{locationService.findLatest(x, y)} returns true

        val result = locationService.findLatest(x,y)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun `get no active locations`(){

        val x = 9L
        val y = 8L

        val locationList =  mutableListOf(
            Location(10, 5, false),
            Location(26, 4, false),
            Location(15, 17, true)
        )

        val locationService = mockk<LocationService>()
        every{locationService.findLatest(x, y)} returns false

        val result = locationService.findLatest(x,y)
        assertThat(result).isEqualTo(false)
    }


}