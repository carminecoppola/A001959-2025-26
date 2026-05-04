package com.example.l15roomdatabasedemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * ItemDao defines database operations.
 *
 * Room generates the implementation at compile time.
 * This gives compile-time validation of SQL queries.
 */
@Dao
interface ItemDao {

    /**
     * Returns all items as a Flow.
     *
     * Flow automatically emits a new list whenever the database changes.
     */
    @Query("SELECT * FROM items ORDER BY created_at DESC")
    fun getAllItems(): Flow<List<Item>>

    /**
     * Inserts an item.
     *
     * suspend means this function must be called from a coroutine.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    /**
     * Deletes an item from the database.
     */
    @Delete
    suspend fun delete(item: Item)

    /**
     * Removes all completed items.
     *
     * This method is included to show a simple custom SQL query.
     */
    @Query("DELETE FROM items WHERE is_done = 1")
    suspend fun clearCompleted()
}