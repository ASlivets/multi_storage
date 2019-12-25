package com.jedicoder.storage

import org.slf4j.LoggerFactory

class DummyStorage : Storage {
    private val logger =  LoggerFactory.getLogger(javaClass)

    override fun create(storeName: String, entry: String): Boolean {
        logger.info("Entry $entry was successfully added to the storage $storeName")

        return true
    }

    override fun update(storeName: String, oldEntry: String, newEntry: String): Boolean {
        logger.info("Entry $oldEntry was successfully updated in the storage $storeName")

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
