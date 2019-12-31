package com.jedicoder

import com.jedicoder.storage.Storage
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class CrudController(private val storage: Storage) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/v1/create")
    fun create(
        @RequestParam(
            "storageName",
            required = true
        ) storageName: String,
        @RequestParam(
            "entry",
            required = true
        ) entry: String
    ): ModelAndView {
        val isCreated = storage.create(storageName, entry)
        val status = if (isCreated) "successfully" else "not"
        logger.debug("Record {} was {} added to the storage {}", entry, status, storageName)

        return read(storageName)
    }

    @RequestMapping("/v1/update")
    fun resources(
        @RequestParam(
            "storageName",
            required = true
        ) storageName: String,
        @RequestParam(
            "oldValue",
            required = true
        ) oldValue: String,
        @RequestParam(
            "newValue",
            required = true
        ) newValue: String
    ): ModelAndView {
        storage.update(storageName, oldValue, newValue)

        return read(storageName)
    }

    @RequestMapping("/v1/read")
    fun read(
        @RequestParam(
            "storageName",
            required = true
        ) storageName: String
    ): ModelAndView {
        val entries = storage.read(storageName)

        val wrappedEntries = entries.map { Entry(it) }
        return ModelAndView("listView", mapOf("entries" to wrappedEntries, "storageName" to storageName))
    }

    private data class Entry(val entry: String)

    @RequestMapping("/v1/delete")
    fun delete(
        @RequestParam(
            "storageName",
            required = true
        ) storageName: String,
        @RequestParam(
            "entry",
            required = true
        ) entry: String
    ): ModelAndView {
        val isDeleted = storage.delete(storageName, entry)
        val status = if (isDeleted) "successfully" else "not"
        logger.debug("Record {} was {} deleted from {}", entry, status, storageName)

        return read(storageName)
    }
}
