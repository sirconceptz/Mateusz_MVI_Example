package com.hermanowicz.mateuszmviexample.data

import com.hermanowicz.mateuszmviexample.domain.repositories.CounterRepository
import com.hermanowicz.mateuszmviexample.domain.usecases.ObserveCounterUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCounterUseCaseImpl @Inject constructor(
    private val counterRepository: CounterRepository
) : ObserveCounterUseCase {

    override fun observeCounter(): Flow<Int> {
        return counterRepository.observeCounter()
    }
}