package com.hermanowicz.mateuszmviexample

import androidx.lifecycle.ViewModel
import com.hermanowicz.mateuszmviexample.data.IncreaseCounterUseCaseImpl
import com.hermanowicz.mateuszmviexample.data.ObserveCounterUseCaseImpl
import com.tomcz.ellipse.Processor
import com.tomcz.ellipse.common.processor
import com.tomcz.ellipse.common.toNoAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

typealias CounterProcessor = Processor<CounterEvents, CounterState, Unit>

@HiltViewModel
class CounterViewModel @Inject constructor(
    // should be used interfaces, not the implementation: https://developer.android.com/training/dependency-injection/hilt-android#inject-interfaces
    private val increaseCounterUseCaseImpl: IncreaseCounterUseCaseImpl,
    observeCounterUseCaseImpl: ObserveCounterUseCaseImpl
) : ViewModel() {

    val processor: CounterProcessor = processor(
        initialState = CounterState(),
        prepare = {
            observeCounterUseCaseImpl.observeCounter()
                .map { CounterPartialState.UpdateCounter(it) }
        },
        onEvent = { event ->
            when (event) {
                is CounterEvents.IncreaseCounter -> {
                    val currentValue = observeCounterUseCaseImpl.observeCounter().first()
                    increaseCounterUseCaseImpl.increaseCounter(currentValue + 1).toNoAction()
                }
            }
        }
    )
}