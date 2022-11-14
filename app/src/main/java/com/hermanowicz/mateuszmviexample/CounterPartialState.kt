package com.hermanowicz.mateuszmviexample

import com.tomcz.ellipse.PartialState

sealed interface CounterPartialState : PartialState<CounterState> {
    data class UpdateCounter(private val counter: Int) : CounterPartialState {
        override fun reduce(oldState: CounterState): CounterState {
            return oldState.copy(count = counter)
        }
    }
}