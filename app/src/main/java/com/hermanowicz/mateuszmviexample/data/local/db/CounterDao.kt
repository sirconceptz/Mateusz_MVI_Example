package com.hermanowicz.mateuszmviexample.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hermanowicz.mateuszmviexample.data.model.Counter
import kotlinx.coroutines.flow.Flow

@Dao
interface CounterDao {
    @Query("SELECT COUNT(*) FROM counter")
    fun observeCounter(): Flow<Int>

    @Insert
    suspend fun insertOrUpdateCounter(counter: Counter)
}