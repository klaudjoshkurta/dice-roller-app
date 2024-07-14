package com.klaudjoshkurta.thoughts.data.model

import java.util.UUID

data class Thought(
    val id: UUID = UUID.randomUUID(),
    val text: String
)