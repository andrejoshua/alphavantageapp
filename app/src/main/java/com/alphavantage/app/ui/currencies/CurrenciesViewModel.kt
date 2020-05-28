package com.alphavantage.app.ui.currencies

import androidx.lifecycle.*
import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.general.Currency
import com.alphavantage.app.domain.usecase.general.FetchCurrencies
import com.alphavantage.app.domain.widget.DispatcherProvider
import com.alphavantage.app.nav.NavManager
import com.alphavantage.app.util.asLiveData
import kotlinx.coroutines.launch
import timber.log.Timber

class CurrenciesViewModel(
    private val navManager: NavManager,
    private val fetchCurrencies: FetchCurrencies,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _items = MediatorLiveData<Result<List<Currency>>>()
    val items = _items.asLiveData()

    fun itemClick(item: Currency) {
        Timber.i(item.name)

        val currentArg = navManager.getArgs()
        if (currentArg is CurrenciesFragmentArgs) {
            // TODO set right as null if from, and other way around
            navManager.navigate(
                CurrenciesFragmentDirections.actionCurrenciesFragmentPop(
                    item,
                    currentArg.viewType
                )
            )
        }
    }

    fun getItems() {
        viewModelScope.launch {
            val item = fetchCurrencies.execute(dispatcherProvider)
                .asLiveData(dispatcherProvider.io() + viewModelScope.coroutineContext)
            _items.addSource(item) {
                _items.postValue(it)
            }
        }
    }
}