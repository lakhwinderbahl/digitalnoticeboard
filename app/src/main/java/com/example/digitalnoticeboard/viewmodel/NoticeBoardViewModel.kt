package com.example.digitalnoticeboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digitalnoticeboard.model.Policy

class NoticeBoardViewModel : ViewModel() {

    private val _policies = MutableLiveData<List<Policy>>()
    val policies: LiveData<List<Policy>> = _policies

    private val _mainPolicy = MutableLiveData<Policy>()
    val mainPolicy: LiveData<Policy> = _mainPolicy

    init {
        loadPolicies()
    }

    private fun loadPolicies() {
        val samplePolicies = listOf(
            Policy("Policy 1", "This is the content of Policy 1", System.currentTimeMillis()),
            Policy("Policy 2", "This is the content of Policy 2", System.currentTimeMillis()),
            Policy("Policy 3", "This is the content of Policy 3", System.currentTimeMillis())
        )
        _policies.value = samplePolicies
        _mainPolicy.value = samplePolicies.first()
    }

    fun selectPolicy(policy: Policy) {
        _mainPolicy.value = policy
    }
}
