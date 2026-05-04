package com.example.l15roomdatabasedemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Item is a Room Entity.
 *
 * In Room, an Entity represents a table in the SQLite database.
 * Each property of this data class becomes a column.
 */
@Entity(tableName = "items")
data class Item(

    /**
     * Primary key generated automatically by Room.
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /**
     * Title column.
     */
    @ColumnInfo(name = "title")
    val title: String,

    /**
     * Boolean column used to represent completion state.
     */
    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,

    /**
     * Timestamp used to order items by creation time.
     */
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)