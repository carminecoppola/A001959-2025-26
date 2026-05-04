package com.example.l15roomdatabasedemo

import kotlinx.coroutines.flow.Flow

/**
 * Repository layer.
 *
 * In MVVM architecture, the Repository is the single source of truth for data.
 * It hides the concrete database implementation from the ViewModel.
 */
class ItemsRepository(
    private val itemDao: ItemDao
) {

    /**
     * Flow of all items from Room.
     *
     * The UI will update automatically when the database content changes.
     */
    val allItems: Flow<List<Item>> = itemDao.getAllItems()

    /**
     * Inserts a new item into the database.
     */
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    /**
     * Deletes an item from the database.
     */
    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }

    /**
     * Deletes completed items.
     */
    suspend fun clearCompleted() {
        itemDao.clearCompleted()
    }
}