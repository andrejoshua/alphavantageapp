package com.alphavantage.app.ui.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.alphavantage.app.R
import com.alphavantage.app.databinding.CurrenciesItemBinding
import com.alphavantage.app.domain.model.general.Currency
import com.alphavantage.app.ui.common.DataBoundListAdapter

class CurrenciesAdapter(private val viewModel: CurrenciesViewModel) :
    DataBoundListAdapter<Currency, CurrenciesItemBinding>(diffCallback = object :
        DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.code == newItem.code
        }
    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int): CurrenciesItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.currencies_item,
            parent,
            false
        )

    override fun bind(binding: CurrenciesItemBinding, item: Currency) {
        binding.item = item
        binding.viewModel = viewModel
    }
}