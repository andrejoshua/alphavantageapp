package com.alphavantage.app.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alphavantage.app.databinding.CurrenciesFragmentBinding
import com.alphavantage.app.domain.model.Result
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrenciesFragment : Fragment() {

    private lateinit var adapter: CurrenciesAdapter
    private lateinit var binding: CurrenciesFragmentBinding

    private val viewModel: CurrenciesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrenciesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvCurrencies.adapter = null
        binding.rvCurrencies.removeAllViews()
    }

    private fun initializeList() {
        adapter = CurrenciesAdapter(viewModel)
        binding.rvCurrencies.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner,
            Observer { result ->
                when (result.status) {
                    Result.Status.LOADING -> {
                        binding.statusView.visibility = View.VISIBLE
                        binding.rvCurrencies.visibility = View.GONE
                        binding.statusView.text = "Loading"

                        result.status.let {
                            adapter.submitList(emptyList())
                        }
                    }
                    Result.Status.ERROR -> {
                        binding.statusView.visibility = View.VISIBLE
                        binding.rvCurrencies.visibility = View.GONE
                        binding.statusView.text = "Error"

                        result.message.let {
                            Snackbar.make(binding.root, it!!, Snackbar.LENGTH_LONG).show()
                        }
                    }
                    Result.Status.SUCCESS -> {
                        binding.statusView.visibility = View.GONE
                        binding.rvCurrencies.visibility = View.VISIBLE

                        result.data?.let { adapter.submitList(it) }
                    }
                }
            })
    }
}
