package com.jedicoder

import io.restassured.RestAssured
import io.restassured.http.Method
import org.hamcrest.CoreMatchers
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticControllerTest(@LocalServerPort val port: Int) {

    @ParameterizedTest
    @ValueSource(strings = ["create", "read", "update", "delete"])
    fun testStaticEndpoint(endpoint: String) {
        RestAssured.given().port(port)
            .log().all()
            .request(Method.GET, endpoint)
            .then()
            .statusCode(200)
            .contentType(MediaType.TEXT_HTML.type)
            .body("", CoreMatchers.notNullValue())
    }
}
