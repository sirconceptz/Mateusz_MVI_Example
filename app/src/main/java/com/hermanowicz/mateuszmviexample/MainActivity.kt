package com.hermanowicz.mateuszmviexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hermanowicz.mateuszmviexample.data.local.db.CounterDb
import com.hermanowicz.mateuszmviexample.databinding.ActivityMainBinding
import com.hermanowicz.mviexample.CounterProcessor
import com.hermanowicz.mviexample.CounterViewModel
import com.tomcz.ellipse.common.clicks
import com.tomcz.ellipse.common.collectAsState
import com.tomcz.ellipse.common.onProcessor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CounterViewModel
    private lateinit var processor : CounterProcessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)
        processor = viewModel.processor

        val view = binding.root
        setContentView(view)

        onProcessor(
            lifecycleState = Lifecycle.State.STARTED,
            processor = viewModel::processor,
            viewEvents = ::viewEvents,
            onState = ::render
        )
        observeLivedata()
    }

    private fun render(state: CounterState) {
        binding.counter.text = state.count.toString()
    }

    private fun viewEvents() = listOf(
        binding.incrementCounterButton.clicks()
            .map { CounterEvents.IncreaseCounter(1) }
    )

    private fun observeLivedata(){
        viewModel.counter.observe(this, counterObserver)
    }

    private val counterObserver = Observer<Int> { newCounter ->
        processor.state.value.count = newCounter
        processor.sendEvent(CounterEvents.ObserveCounter(0))
    }
}