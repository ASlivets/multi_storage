package com.jedicoder

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DummyStorageTest {
    private  val entry = "dummyEntry"
    private  val storageName = "dummyName"

    private val storage: Storage = DummyStorage()

    @Test
    fun `when error occurs during creating then return false`() {
        assertThat(storage.create(storageName, entry)).isFalse()
    }

    @Test
    fun `when successfully create an entry in the file then return true`() {
        assertThat(storage.create(storageName, entry)).isTrue()
    }

    @Test
    fun `when an entry was not updated then return false`() {
        assertThat(storage.update(storageName, entry, "new")).isFalse()
    }

    @Test
    fun `when an entry was successfully updated in the file then return true`() {
        assertThat(storage.update(storageName, entry, "new")).isTrue()
    }

    @Test
    fun `when read a storage then return not empty list of entries`() {
        val entries = storage.read(storageName)

        assertThat(entries).isNotEmpty
    }

    @Test
    fun `when an entry wasn't deleted then return false`() {
        assertThat(storage.delete(storageName, entry)).isFalse()
    }

    @Test
    fun `when an entry was successfully deleted then return true`() {
        assertThat(storage.delete(storageName, entry)).isTrue()
    }
}