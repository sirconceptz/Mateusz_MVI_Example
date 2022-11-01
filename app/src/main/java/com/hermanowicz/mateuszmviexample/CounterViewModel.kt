package com.hermanowicz.mviexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hermanowicz.mateuszmviexample.CounterEvents
import com.hermanowicz.mateuszmviexample.CounterPartialState
import com.hermanowicz.mateuszmviexample.CounterState
import com.hermanowicz.mateuszmviexample.data.IncreaseCounterUseCaseImpl
import com.hermanowicz.mateuszmviexample.data.ObserveCounterUseCaseImpl
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias CounterProcessor = Processor<CounterEvents, CounterState, Unit>

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val increaseCounterUseCaseImpl: IncreaseCounterUseCaseImpl,
    private val observeCounterUseCaseImpl: ObserveCounterUseCaseImpl
) : ViewModel() {

    val counter: LiveData<Int> = observeCounterUseCaseImpl.observeCounter()

    val processor: CounterProcessor = processor(CounterState()){ event ->
        when(event){
            is CounterEvents.IncreaseCounter -> flow{
                increaseCounterUseCaseImpl.increaseCounter(state.value.count)
                emit(CounterPartialState.IncreaseCounterPartialState)
            }
        }
    }
}