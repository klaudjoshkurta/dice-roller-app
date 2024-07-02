package com.klaudjoshkurta.thoughts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thoughts")
data class Thought(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String
)
