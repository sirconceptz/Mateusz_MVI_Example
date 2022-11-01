package com.hermanowicz.mateuszmviexample

import com.tomcz.ellipse.PartialState

sealed interface CounterPartialState : PartialState<CounterState> {
    object IncreaseCounterPartialState : CounterPartialState {
        override fun reduce(oldState: CounterState): CounterState {
            return oldState.copy(count = oldState.count + 1)
        }
    }

    object ObserveCounterPartialState : CounterPartialState {
        override fun reduce(oldState: CounterState): CounterState {
            return oldState.copy(count = oldState.count + 1)
        }
    }
}