package com.hermanowicz.mateuszmviexample.domain.usecases

interface IncreaseCounterUseCase {
    suspend fun increaseCounter(currentValue: Int)
}