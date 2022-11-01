package com.hermanowicz.mateuszmviexample

sealed class CounterEvents {
    data class IncreaseCounter(val count: Int) : CounterEvents()
}