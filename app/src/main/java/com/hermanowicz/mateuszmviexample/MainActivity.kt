package com.hermanowicz.mateuszmviexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.hermanowicz.mateuszmviexample.data.local.db.CounterDb
import com.hermanowicz.mateuszmviexample.databinding.ActivityMainBinding
import com.hermanowicz.mviexample.CounterProcessor
import com.hermanowicz.mviexample.CounterViewModel
import com.tomcz.ellipse.common.clicks
import com.tomcz.ellipse.common.collectAsState
import com.tomcz.ellipse.common.onProcessor
import dagger.hilt.android.AndroidEntryPoint
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
    }

    private fun render(state: CounterState) {
        binding.counter.text = state.count.toString()
        val count = CounterDb.getInstance(this).counterDao().getCountValue()
        Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun viewEvents() = listOf(
        binding.incrementCounterButton.clicks()
            .map { CounterEvents.IncreaseCounter(1) }
    )

    private fun onClickIncrementCounter(view: View) {
        val event = CounterEvents.IncreaseCounter(1)
        processor.sendEvent(event)
    }
}