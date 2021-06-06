package com.example.kittens.repository

import com.example.kittens.model.Direction
import com.example.kittens.model.Location
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime
import java.time.Month

@DataJpaTest
class RepositoriesTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val directionRepository: DirectionRepository,
    val locationRepository: LocationRepository){

    @Test
    fun `When findAll then return a direction`() {
        val dateTime = LocalDateTime.of(2021, Month.MAY, 15, 3, 15)
        val firstString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ac faucibus dui, at suscipit dui."
        val direction = Direction(dateTime, firstString)
        entityManager.persistAndFlush(direction)
        val found = directionRepository.findAll()
        assertThat(found.count()).isEqualTo(1)
        assertThat(found.first().directionString).isEqualTo(firstString)
    }

    @Test
    fun `When findAll then return two directions`() {
        val dateTime1 = LocalDateTime.of(2021, Month.MAY, 15, 3, 15)
        val firstString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ac faucibus dui, at " +
                "suscipit dui."
        val direction1 = Direction(dateTime1, firstString)

        val dateTime2 = LocalDateTime.of(2021, Month.MAY, 16, 9, 18)
        val secondString = "Aenean ac nisi non libero faucibus aliquam. Duis molestie ligula magna, quis hendrerit" +
                " nibh mollis in. Maecenas vitae consequat mi."
        val direction2 = Direction(dateTime2, secondString)

        entityManager.persist(direction1)
        entityManager.persist(direction2)
        entityManager.flush()
        val found = directionRepository.findAll()
        assertThat(found.count()).isEqualTo(2)
        assertThat(found.first().directionDate).isEqualTo(dateTime1)
        assertThat(found.last().directionString).isEqualTo(secondString)
    }

    @Test
    fun `When findAll then return a location`() {
        val dateTime = LocalDateTime.of(2021, Month.MAY, 15, 3, 15)
        val location = Location(10, 5, true)
        entityManager.persistAndFlush(location)
        val found = locationRepository.findAll()
        assertThat(found.count()).isEqualTo(1)
        assertThat(found.first().yLocation).isEqualTo(5L)
    }

    @Test
    fun `When findAll then return muliple locations`() {
        val location = Location(10, 5, false)
        val location1 = Location(26, 4, false)
        val location2 = Location(100, 3, true)
        entityManager.persist(location)
        entityManager.persist(location1)
        entityManager.persist(location2)
        entityManager.flush()
        val found = locationRepository.findAll()
        assertThat(found.count()).isEqualTo(3)
        assertThat(found.first().yLocation).isEqualTo(5L)
        assertThat(found.last().isActive).isEqualTo(true)
    }


}