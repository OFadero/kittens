package com.example.kittens.controller

import com.example.kittens.model.Direction
import com.example.kittens.repository.DirectionRepository
import com.example.kittens.service.DirectionService
import com.example.kittens.service.LocationService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime
import java.time.Month


@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KittensControllerTest (@Autowired val mockMvc: MockMvc){

    @MockkBean
    private lateinit var directionService: DirectionService

    @MockkBean
    private lateinit var locationService: LocationService

    @Test
    fun `fetch Directions`() {
       val dateTime1 = LocalDateTime.of(2021, Month.MAY, 15, 3, 15)
       val dateTime2 = LocalDateTime.of(2021, Month.MAY, 16, 9, 18)
       val directionsList = listOf(
            Direction(dateTime1, "aString",1),
            Direction(dateTime2, "bString",2))

        val directionRepository = mockk<DirectionRepository>()
        every{directionRepository.findAll()} returns directionsList
        every{directionService.findAll()} returns directionsList.toMutableList()

       //TODO find a json library that reads in a list
        val output = "[{\"directionDate\":\"2021-05-15T03:15:00\",\"directionString\":\"aString\",\"directionId\":1}," +
                "{\"directionDate\":\"2021-05-16T09:18:00\",\"directionString\":\"bString\",\"directionId\":2}]"
        val emailAddress = "abc%40gmail.com"

        mockMvc.perform( MockMvcRequestBuilders.get("/api/$emailAddress/directions")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string(output))
    }

    @Test
    fun `fetch Directions with invalid email`() {
        val emailAddress = "1234."
        mockMvc.perform( MockMvcRequestBuilders.get("/api/$emailAddress/directions")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun `get location with valid email found`() {
        val emailAddress = "abc%40gmail.com"
        val x = "100"
        val y = "100"

        every{locationService.findLatest(x.toLong(), y.toLong())} returns true

        mockMvc.perform( MockMvcRequestBuilders.get("/api/$emailAddress/directions/$x/$y")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }



    @Test
    fun `get location with valid email not found`() {
        val emailAddress = "abc%40gmail.com"
        val x = "9"
        val y = "8"

       every{locationService.findLatest(x.toLong(), y.toLong())} returns false

       mockMvc.perform( MockMvcRequestBuilders.get("/api/$emailAddress/directions/$x/$y")
           .accept(MediaType.APPLICATION_JSON))
           .andExpect(MockMvcResultMatchers.status().isNotFound)
    }


}