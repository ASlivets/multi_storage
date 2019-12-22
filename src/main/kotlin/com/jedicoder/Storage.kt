package com.jedicoder

interface Storage {

    /**
     * Create a new entry in the storage
     * If storage doesn't exist it will be created
     *
     * @param storeName the name of the storage,
     * @param entry the entry to be created
     *
     * @return `true` in case of success or `false` otherwise
     */
    fun create(
        storeName: String,
        entry: String
    ): Boolean

    /**
     * Update an entry in the storage
     * If storage doesn't exist it will be created
     *
     * @param storeName the name of the storage,
     * @param oldEntry the entry to be created
     *
     * @return `true` in case of success or `false` otherwise
     */
    fun update(
        storeName: String,
        oldEntry: String,
        newEntry: String
    ): Boolean

    /**
     * Read the storage
     *
     * @param storeName the name of the storage to read
     *
     * @return list of entries from the storage
     */
    fun read(storeName: String): List<String>

    /**
     * Delete an entry from the storage
     *
     * @param storeName the name of the storage,
     * @param entry the entry to be deleted
     *
     * @return `true` in case of success or `false` otherwise
     */
    fun delete(
        storeName: String,
        entry: String
    ): Boolean
}