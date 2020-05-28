package com.alphavantage.app.ui.common

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

open class DataBoundViewHolder<out T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun markAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun markDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}