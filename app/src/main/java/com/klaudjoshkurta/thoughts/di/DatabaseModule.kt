package com.klaudjoshkurta.thoughts.di

import android.content.Context
import androidx.room.Room
import com.klaudjoshkurta.thoughts.database.ThoughtDao
import com.klaudjoshkurta.thoughts.database.ThoughtsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ThoughtsDatabase {
        return Room.databaseBuilder(context, ThoughtsDatabase::class.java, "thoughts_db").build()
    }

    @Provides
    fun provideThoughtDao(database: ThoughtsDatabase): ThoughtDao {
        return database.thoughtDao()
    }
}