package com.hermanowicz.mateuszmviexample.data

import com.hermanowicz.mateuszmviexample.domain.repositories.CounterRepository
import com.hermanowicz.mateuszmviexample.domain.usecases.IncreaseCounterUseCase
import javax.inject.Inject

class IncreaseCounterUseCaseImpl @Inject constructor(
    private val counterRepository: CounterRepository
) : IncreaseCounterUseCase {

    override fun increaseCounter(currentValue: Int) {
        counterRepository.increaseCounter(currentValue)
    }
}