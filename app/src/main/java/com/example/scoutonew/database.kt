package com.example.scoutonew

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class,carInfo::class], version = 1, exportSchema = false)
abstract class database: RoomDatabase()  {

        abstract fun mydao(): myDao
        abstract fun newdao():newDao

        companion object {
            @Volatile
            var INSTANCE: database? = null

            fun getcontext(context: Context): database {
                if (INSTANCE == null) {
                    synchronized(this) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext, database::class.java, "roomDb"
                        ).build()
                    }
                }
                return INSTANCE!!
            }

        }
    }
