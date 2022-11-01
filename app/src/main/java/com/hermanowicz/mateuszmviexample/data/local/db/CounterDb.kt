package com.hermanowicz.mateuszmviexample.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hermanowicz.mateuszmviexample.data.model.Counter

@Database(entities = [Counter::class], version = 1)
abstract class CounterDb : RoomDatabase() {

    abstract fun counterDao(): CounterDao

    companion object {

        private var INSTANCE: CounterDb? = null
        private val sLock = Any()

        fun getInstance(context: Context): CounterDb {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CounterDb::class.java, "CounterDb.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE as CounterDb
            }
        }
    }
}