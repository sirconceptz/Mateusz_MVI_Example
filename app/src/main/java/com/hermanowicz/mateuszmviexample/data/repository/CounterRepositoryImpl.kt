package com.hermanowicz.mateuszmviexample.data.repository

import com.hermanowicz.mateuszmviexample.data.local.db.CounterDao
import com.hermanowicz.mateuszmviexample.data.model.Counter
import com.hermanowicz.mateuszmviexample.domain.repositories.CounterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(
    private val counterDao: CounterDao
) : CounterRepository {

    override fun observeCounter(): Flow<Int> {
        return counterDao.observeCounter()
    }

    override suspend fun increaseCounter(currentValue: Int) {
        val counter = Counter(0)
        counterDao.insertOrUpdateCounter(counter)
    }
}