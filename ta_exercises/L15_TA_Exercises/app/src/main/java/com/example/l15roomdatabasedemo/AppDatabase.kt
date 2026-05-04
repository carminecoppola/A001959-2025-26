package com.example.l15roomdatabasedemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * AppDatabase is the Room database holder.
 *
 * It lists all entities and exposes DAO objects.
 * It must be created as a singleton to avoid opening multiple database instances.
 */
@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to Item database operations.
     */
    abstract fun itemDao(): ItemDao

    companion object {

        /**
         * Volatile guarantees that changes to INSTANCE are visible across threads.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Returns the singleton database instance.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "l15_room_database"
                )
                    /*
                        This is acceptable only for a classroom demo.
                        In production, migrations should be written explicitly.
                    */
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}