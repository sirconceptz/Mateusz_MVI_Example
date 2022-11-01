package com.hermanowicz.mateuszmviexample

sealed class CounterEvents {
    data class IncreaseCounter(val count: Int) : CounterEvents()
    data class ObserveCounter(val count: Int) : CounterEvents()
}