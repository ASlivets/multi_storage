package com.jedicoder

import com.jedicoder.storage.Storage
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

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
    ): ModelAndView {
        val isCreated = storage.create(storeName, entry)

        return ModelAndView("result", mapOf("status" to isCreated))
    }
}
