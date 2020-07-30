package com.alphavantage.app.ui.exchangerate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import com.alphavantage.app.databinding.ExchangeRateFragmentBinding
import com.alphavantage.app.ui.common.setOnSafeClickListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ExchangeRateFragment : Fragment() {

    companion object {
        fun newInstance() =
            ExchangeRateFragment()
    }

    // Using shared ViewModel to retain previous instance
    private val viewModel: ExchangeRateViewModel by sharedViewModel()

    private lateinit var binding: ExchangeRateFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExchangeRateFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initValue()

        viewModel.fromCurrency.map { it.toString() }.observe(viewLifecycleOwner, Observer {
            binding.fromEditText.text = it
        })
        viewModel.toCurrency.map { it.toString() }.observe(viewLifecycleOwner, Observer {
            binding.toEditText.text = it
        })
        viewModel.output.map { it.toString() }.observe(viewLifecycleOwner, Observer {
            binding.priceOutputText.text = it
        })

        binding.priceInputEdit.doOnTextChanged { _, _, _, _ ->
            viewModel.input.value = binding.priceInputEdit.text.toString()
        }

        binding.calculateButton.setOnClickListener {
            viewModel.calculate()
        }

        binding.fromCurrencyLayout.setOnSafeClickListener {
            viewModel.setGoToFromCurrenciesAction()
        }

        binding.toCurrencyLayout.setOnSafeClickListener {
            viewModel.setGoToToCurrenciesAction()
        }
    }
}
