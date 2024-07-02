package com.klaudjoshkurta.thoughts.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.klaudjoshkurta.thoughts.database.ThoughtDao
import com.klaudjoshkurta.thoughts.model.Thought
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThoughtsRepository @Inject constructor(
    private val thoughtDao: ThoughtDao,
    private val firestore: FirebaseFirestore
) {

    val allThoughts: Flow<List<Thought>> = thoughtDao.getAll()

    suspend fun insert(thought: Thought) {
        thoughtDao.insert(thought)
        firestore.collection("thoughts").add(thought)
    }

    suspend fun delete(thought: Thought) {
        thoughtDao.delete(thought)
        firestore.collection("thoughts").document(thought.id.toString()).delete()
    }
}