package com.jedicoder

import com.jedicoder.storage.Storage
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class CrudController(private val storage: Storage) {

    @RequestMapping("/v1/create")
    fun create(
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

    @RequestMapping("/v1/update")
    fun resources(
        @RequestParam(
            "storeName",
            required = true
        ) storeName: String,
        @RequestParam(
            "oldValue",
            required = true
        ) oldValue: String,
        @RequestParam(
            "newValue",
            required = true
        ) newValue: String
    ): ModelAndView {
        val isUpdated = storage.update(storeName, oldValue, newValue)

        return ModelAndView("result", mapOf("status" to isUpdated))
    }

    @RequestMapping("/v1/read")
    fun read(
        @RequestParam(
            "storeName",
            required = true
        ) storeName: String
    ): ModelAndView {
        val entries = storage.read(storeName)

        return ModelAndView("result", mapOf("entries" to entries))
    }

    @RequestMapping("/v1/delete")
    fun delete(
        @RequestParam(
            "storeName",
            required = true
        ) storeName: String,
        @RequestParam(
            "entry",
            required = true
        ) entry: String
    ): ModelAndView {
        val isDeleted = storage.delete(storeName, entry)

        return ModelAndView("result", mapOf("status" to isDeleted))
    }
}
