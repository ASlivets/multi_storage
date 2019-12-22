package com.jedicoder

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test
import java.io.File

class FileStorageTest {
    private val entry = "dummyEntry"
    private val storageName = "dummyName"

    private val storage: Storage = FileStorage()

    @After
    fun tearDown() {
        File(storageName).delete()
    }

    @Test
    fun `when error occurs during creating then return false`() {
        storage.create(storageName, entry)
        assertThat(storage.create(storageName, entry)).isFalse()
    }

    @Test
    fun `when successfully create an entry in the file then return true`() {
        assertThat(storage.create(storageName, entry)).isTrue()
    }

    @Test
    fun `when an entry was not updated then return false`() {
        assertThat(storage.update(storageName, entry, entry)).isFalse()
    }

    @Test
    fun `when an entry was successfully updated in the file then return true`() {
        storage.create(storageName, entry)
        assertThat(storage.update(storageName, entry, "new")).isTrue()
    }

    @Test
    fun `when read a storage then return not empty list of entries`() {
        storage.create(storageName, entry)
        val entries = storage.read(storageName)

        assertThat(entries).isNotEmpty
    }

    @Test
    fun `when an entry wasn't deleted then return false`() {
        assertThat(storage.delete(storageName, entry)).isFalse()
    }

    @Test
    fun `when an entry was successfully deleted then return true`() {
        storage.create(storageName, entry)
        assertThat(storage.delete(storageName, entry)).isTrue()
    }
}