package com.hermanowicz.mateuszmviexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.hermanowicz.mateuszmviexample.databinding.ActivityMainBinding
import com.tomcz.ellipse.common.clicks
import com.tomcz.ellipse.common.onProcessor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<CounterViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var processor: CounterProcessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        processor = viewModel.processor

        val view = binding.root
        setContentView(view)

        onProcessor(
            lifecycleState = Lifecycle.State.STARTED,
            processor = viewModel::processor,
            viewEvents = ::viewEvents,
            onState = ::render
        )
    }

    private fun render(state: CounterState) {
        binding.counter.text = state.count.toString()
    }

    private fun viewEvents() = listOf(
        binding.incrementCounterButton.clicks()
            .map { CounterEvents.IncreaseCounter }
    )
}