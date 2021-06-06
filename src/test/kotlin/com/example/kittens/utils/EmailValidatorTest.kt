package com.example.kittens.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmailValidatorTest {

    @Test
    fun `When put in valid email then return true`() {
        assertTrue(EmailValidator.isEmailValid("abc%40somecomany.com"))
        assertTrue(EmailValidator.isEmailValid("hno.body101u%40werkuldino.buzz"))
        assertTrue(EmailValidator.isEmailValid("user%example.com%40example.org"))
    }


    @Test
    fun `When put in invalid email then return false`() {
        assertFalse(EmailValidator.isEmailValid("someone"))
        assertFalse(EmailValidator.isEmailValid("someone%40com"))
        assertFalse(EmailValidator.isEmailValid("someone-two%40"))
    }
}