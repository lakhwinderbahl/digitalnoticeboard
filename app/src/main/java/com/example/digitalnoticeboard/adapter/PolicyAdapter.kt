package com.example.digitalnoticeboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalnoticeboard.databinding.ItemPolicyBinding
import com.example.digitalnoticeboard.model.Policy

class PolicyAdapter(
    private val onClick: (Policy) -> Unit
) : RecyclerView.Adapter<PolicyAdapter.PolicyViewHolder>() {

    private val policies = mutableListOf<Policy>()

    inner class PolicyViewHolder(private val binding: ItemPolicyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(policy: Policy) {
            binding.policyTitle.text = policy.title
            binding.root.setOnClickListener { onClick(policy) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyViewHolder {
        val binding = ItemPolicyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PolicyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PolicyViewHolder, position: Int) {
        holder.bind(policies[position])
    }

    override fun getItemCount(): Int = policies.size

    fun submitList(newPolicies: List<Policy>) {
        policies.clear()
        policies.addAll(newPolicies)
        notifyDataSetChanged()
    }
}
