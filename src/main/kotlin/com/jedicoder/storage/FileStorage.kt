package com.jedicoder.storage

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class FileStorage : Storage {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun create(storeName: String, entry: String): Boolean {
        val file = File(storeName)
        val entries = readEntries(file)
        return if (entries.contains(entry)) {
            logger.warn("Can't create an entry $entry in the file $storeName because it is already present")
            false
        } else {
            val appendedList = entries.joinToString("\n")
            file.writeText(if (appendedList.isEmpty()) appendedList.plus(entry) else appendedList.plus('\n').plus(entry))
            logger.info("Entry $entry was successfully added to the storage $storeName")
            true
        }
    }

    override fun update(storeName: String, oldEntry: String, newEntry: String): Boolean {
        val file = File(storeName)
        val entries = readEntries(file)
        val indexOf = entries.indexOf(oldEntry)
        return if (indexOf == -1) {
            logger.warn("Entry $oldEntry doesn't exist in the storage $storeName")
            false
        } else {
            val strings = entries.toTypedArray()
            strings[indexOf] = newEntry
            val appendedList = strings.joinToString("\n")
            file.writeText(appendedList)
            logger.info("Entry $newEntry was successfully updated in the storage $storeName")
            true
        }
    }

    override fun delete(storeName: String, entry: String): Boolean {
        val file = File(storeName)
        val entries = readEntries(file)
        val list = entries.toMutableList()
        val result = list.remove(entry)
        return if (result) {
            val appendedList = list.joinToString("\n")
            file.writeText(appendedList)
            logger.info("Record $entry successfully deleted from the storage $storeName")
            true
        } else {
            logger.warn("Entry $entry doesn't exist in the storage $storeName")
            false
        }
    }

    override fun read(storeName: String): List<String> {
        val file = File(storeName)
        val emptyList = readEntries(file)
        logger.info("Successfully read ${emptyList.size} entries from the storage $storeName")

        return emptyList
    }

    private fun readEntries(file: File): List<String> {
        return try {
            file.readLines()
        } catch (e: Exception) {
            emptyList()
        }
    }
}