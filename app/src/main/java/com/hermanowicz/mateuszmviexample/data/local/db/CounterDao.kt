package com.hermanowicz.mateuszmviexample.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hermanowicz.mateuszmviexample.data.model.Counter


@Dao
interface CounterDao {
    @Query("SELECT COUNT(value) FROM counter")
    fun getCount(): LiveData<Int>

    @Query("SELECT COUNT(value) FROM counter")
    fun getCountValue(): Int

    @Insert
    fun insertOrUpdateCounter(vararg counter: Counter)
}