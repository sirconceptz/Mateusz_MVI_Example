package com.hermanowicz.mateuszmviexample.module

import android.content.Context
import com.hermanowicz.mateuszmviexample.data.local.db.CounterDb
import com.hermanowicz.mateuszmviexample.data.repository.CounterRepositoryImpl
import com.hermanowicz.mateuszmviexample.domain.repositories.CounterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCounterRepository(@ApplicationContext context: Context): CounterRepository {
        val counterDb: CounterDb = CounterDb.getInstance(context)
        val counterDao = counterDb.counterDao()
        return CounterRepositoryImpl(counterDao)
    }
}