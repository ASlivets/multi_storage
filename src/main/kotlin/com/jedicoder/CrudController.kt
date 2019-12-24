package com.jedicoder

import com.jedicoder.model.Result
import com.jedicoder.storage.Storage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CrudController(private val storage: Storage) {

    @RequestMapping("/v1/create")
    fun resources(
        @RequestParam(
            "storeName",
            required = true
        ) storeName: String,
        @RequestParam(
            "entry",
            required = true
        ) entry: String
    ): ResponseEntity<Result> {
        val isCreated = storage.create(storeName, entry)
        return ResponseEntity.ok()
            .body(Result(isCreated))
    }
}
