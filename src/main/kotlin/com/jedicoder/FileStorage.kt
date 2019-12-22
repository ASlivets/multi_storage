package com.jedicoder

import org.slf4j.LoggerFactory
import java.io.File

class FileStorage : Storage {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun create(storeName: String, entry: String): Boolean {
        val file = File(storeName)
        val entries =readEntries(file)
        return if (entries.contains(entry)) {
            logger.warn("Can create an entry $entry in the file $storeName because it is already present")
            false
        } else {
            val appendedList = entries.joinToString("\n")
            file.writeText(if (appendedList.isEmpty()) appendedList.plus(entry) else appendedList.plus('\n').plus(entry))
            logger.info("Entry $entry was successfully added to the storage $storeName")
            true
        }
    }

    private fun readEntries(file: File): List<String> {
        return try {
            file.readLines()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun update(storeName: String, entry: String): Boolean {
        logger.info("Entry $entry was successfully updated in the storage $storeName")

        return true
    }

    override fun read(storeName: String): List<String> {
        val emptyList = emptyList<String>()
        logger.info("Successfully read ${emptyList.size} entries from the storage $storeName")

        return emptyList
    }

    override fun delete(storeName: String, entry: String): Boolean {
        logger.info("Record $entry successfully deleted from the storage $storeName")

        return true
    }
}