package com.hermanowicz.mateuszmviexample.domain.usecases

import androidx.lifecycle.LiveData

interface ObserveCounterUseCase {
    fun observeCounter(): LiveData<Int>
}