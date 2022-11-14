package com.hermanowicz.mateuszmviexample.domain.usecases

import com.hermanowicz.mateuszmviexample.data.model.Counter
import kotlinx.coroutines.flow.Flow


interface ObserveCounterUseCase {
    fun observeCounter(): Flow<Int>
}