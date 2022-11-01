package com.hermanowicz.mateuszmviexample.domain.repositories

import androidx.lifecycle.LiveData

interface CounterRepository {
    fun getCounter(): LiveData<Int>

    fun increaseCounter(currentValue: Int)
}