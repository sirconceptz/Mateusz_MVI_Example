package com.hermanowicz.mateuszmviexample.data

import androidx.lifecycle.LiveData
import com.hermanowicz.mateuszmviexample.domain.repositories.CounterRepository
import com.hermanowicz.mateuszmviexample.domain.usecases.ObserveCounterUseCase
import javax.inject.Inject

class ObserveCounterUseCaseImpl @Inject constructor(
    private val counterRepository: CounterRepository
) : ObserveCounterUseCase {

    override fun observeCounter(): LiveData<Int> {
        return counterRepository.getCounter()
    }
}