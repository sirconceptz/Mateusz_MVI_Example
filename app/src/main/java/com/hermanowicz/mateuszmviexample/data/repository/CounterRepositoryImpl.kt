package com.hermanowicz.mateuszmviexample.data.repository

import androidx.lifecycle.LiveData
import com.hermanowicz.mateuszmviexample.data.local.db.CounterDao
import com.hermanowicz.mateuszmviexample.data.model.Counter
import com.hermanowicz.mateuszmviexample.domain.repositories.CounterRepository
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(
    private val counterDao: CounterDao
) : CounterRepository {

    override fun getCounter(): LiveData<Int> {
        return counterDao.getCount()
    }

    override fun increaseCounter(currentValue: Int) {
        val counter = Counter(0, currentValue + 1)
        counterDao.insertOrUpdateCounter(counter)
    }
}