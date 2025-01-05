package com.example.digitalnoticeboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalnoticeboard.adapter.PolicyAdapter
import com.example.digitalnoticeboard.databinding.ActivityMainBinding
import com.example.digitalnoticeboard.viewmodel.NoticeBoardViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NoticeBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupRecyclerView()
        updateClock()
    }

    private fun setupObservers() {
        // Observe Main Policy
        viewModel.mainPolicy.observe(this) { policy ->
            binding.mainPolicy.text = policy?.content ?: "No policy selected"
        }

        // Observe Policy List
        viewModel.policies.observe(this) { policies ->
            (binding.policySelector.adapter as? PolicyAdapter)?.submitList(policies)
        }
    }

    private fun setupRecyclerView() {
        val adapter = PolicyAdapter { policy ->
            viewModel.selectPolicy(policy)  // Pass the selected policy to the ViewModel
        }
        binding.policySelector.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.policySelector.adapter = adapter
    }

    private fun updateClock() {
        lifecycleScope.launch {
            while (true) {
                try {
                    val currentTime = SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(Date())
                    binding.clock.text = currentTime
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(1000)
            }
        }
    }
}
