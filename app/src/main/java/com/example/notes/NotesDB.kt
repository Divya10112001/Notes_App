package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Notes::class),version=1,exportSchema = false)
abstract class NotesDB:RoomDatabase() {
  abstract  fun getNoteDao():NoteDao
  companion object{
      @Volatile
      private var INSTANCE : NotesDB?=null

       fun getDB(context: Context):NotesDB{
           //if INSTANCE is not null then return it;
           //if it is null,create DB
           return INSTANCE  ?: synchronized(this){
               val instance =Room.databaseBuilder(
                   context.applicationContext,NotesDB::class.java,
                   "notes_DB").build()
               INSTANCE=instance
               //return instance
               instance
           }

       }
  }
}