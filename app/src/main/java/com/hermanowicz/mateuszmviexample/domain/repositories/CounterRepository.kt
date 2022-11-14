package com.hermanowicz.mateuszmviexample.domain.repositories

import kotlinx.coroutines.flow.Flow

interface CounterRepository {
    fun observeCounter(): Flow<Int>

    suspend fun increaseCounter(currentValue: Int)
}