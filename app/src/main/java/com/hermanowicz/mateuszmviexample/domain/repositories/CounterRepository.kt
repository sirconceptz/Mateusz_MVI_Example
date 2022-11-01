package com.hermanowicz.mateuszmviexample.domain.repositories

import androidx.lifecycle.LiveData
import com.hermanowicz.mateuszmviexample.data.model.Counter

interface CounterRepository {
    fun getCounter() : LiveData<Int>

    fun increaseCounter(currentValue: Int)
}