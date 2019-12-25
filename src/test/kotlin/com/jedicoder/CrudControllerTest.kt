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

    @Test
    fun `when get resources then status code is 200`() {
        RestAssured.given().port(port)
            .log().all()
            .queryParam("storeName", "filename42")
            .queryParam("entry", "33")
            .request(Method.POST, "/v1/create")
            .then()
            .statusCode(200)
    }

    @Test
    fun `when requests resources then set content type AppJson`() {
        RestAssured.given().port(port)
            .log().all()
            .queryParam("storeName", "filename42")
            .queryParam("entry", "33")
            .request(Method.POST, "/v1/create")
            .then()
            .contentType(MediaType.TEXT_HTML.type)
    }

    @Test
    fun `when requests resources then body is not empty`() {
        RestAssured.given().port(port)
            .log().all()
            .queryParam("storeName", "filename42")
            .queryParam("entry", "33")
            .request(Method.POST, "/v1/create")
            .then()
            .body("", CoreMatchers.notNullValue())
    }
}
