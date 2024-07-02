package com.klaudjoshkurta.thoughts.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.klaudjoshkurta.thoughts.model.Thought

@Database(entities = [Thought::class], version = 1, exportSchema = false)
abstract class ThoughtsDatabase : RoomDatabase() {
    abstract fun thoughtDao(): ThoughtDao
}