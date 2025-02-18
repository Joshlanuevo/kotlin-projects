package com.vancoding.notetakingapp.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1,
)
abstract class NoteDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "note.db"
        private var instance: NoteDatabase? = null

        private fun create(context: Context): NoteDatabase =
            Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): NoteDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun noteDao(): NoteDao
}