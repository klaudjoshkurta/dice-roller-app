package com.klaudjoshkurta.thoughts.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klaudjoshkurta.thoughts.model.Thought
import kotlinx.coroutines.flow.Flow

@Dao
interface ThoughtDao {
    @Query("SELECT * FROM thoughts")
    fun getAll(): Flow<List<Thought>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(thought: Thought)

    @Delete
    suspend fun delete(thought: Thought)
}