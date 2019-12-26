package com.jedicoder

import io.restassured.RestAssured
import io.restassured.http.Method
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudControllerTest(@LocalServerPort val port: Int) {
    private val storageName = "filename42"

    @Test
    fun `when request 'create' with params then create an entry`() {
        RestAssured.given().port(port)
            .log().all()
            .queryParam("storageName", storageName)
            .queryParam("entry", "33")
            .request(Method.POST, "/v1/create")
            .then()
            .statusCode(200)
            .contentType(MediaType.TEXT_HTML.type)
            .body("", CoreMatchers.notNullValue())
    }

    @Test
    fun `when request 'read' with params then read entries from the storage`() {
        RestAssured.given().port(port)
            .log().all()
            .queryParam("storageName", storageName)
            .request(Method.POST, "/v1/read")
            .then()
            .statusCode(200)
            .contentType(MediaType.TEXT_HTML.type)
            .body("", CoreMatchers.notNullValue())
    }

    @Test
    fun `when request 'update' with params then update an entry`() {
        RestAssured.given().port(port)
            .log().all()
            .queryParam("storageName", storageName)
            .queryParam("oldValue", "33")
            .queryParam("newValue", "42")
            .request(Method.POST, "/v1/update")
            .then()
            .statusCode(200)
            .contentType(MediaType.TEXT_HTML.type)
            .body("", CoreMatchers.notNullValue())
    }

    @Test
    fun `when request 'delete' with params then delete an entry`() {
        RestAssured.given().port(port)
            .log().all()
            .queryParam("storageName", storageName)
            .queryParam("entry", "33")
            .request(Method.POST, "/v1/delete")
            .then()
            .statusCode(200)
            .contentType(MediaType.TEXT_HTML.type)
            .body("", CoreMatchers.notNullValue())
    }
}
