package com.alphavantage.app.ui.exchangerate

import androidx.lifecycle.*
import com.alphavantage.app.domain.model.Result
import com.alphavantage.app.domain.model.general.Currency
import com.alphavantage.app.domain.usecase.forex.CalculateExchangeRate
import com.alphavantage.app.domain.widget.DispatcherProvider
import com.alphavantage.app.nav.NavManager
import com.alphavantage.app.ui.currencies.CurrenciesViewType
import kotlinx.coroutines.launch

// TODO NavArgs
class ExchangeRateViewModel(
    private val navManager: NavManager,
    private val calculateExchangeRate: CalculateExchangeRate,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    // Variables
    private val _fromCurrency = MutableLiveData<Currency?>()
    val fromCurrency: LiveData<String?> get() = _fromCurrency.map { it?.code }

    private val _toCurrency = MutableLiveData<Currency?>()
    val toCurrency: LiveData<String?> get() = _toCurrency.map { it?.code }

    var input = MutableLiveData<String>()

    private val _output = MediatorLiveData<Result<Double?>>()
    val output: LiveData<Double?> = _output.map { it.data }

    fun calculate() {
        viewModelScope.launch {
            val item = calculateExchangeRate.execute(
                _fromCurrency.value,
                _toCurrency.value,
                input.value!!.toDoubleOrNull(),
                dispatcherProvider
            ).asLiveData(dispatcherProvider.io() + viewModelScope.coroutineContext)
            _output.addSource(item) {
                _output.postValue(it)
            }
        }
    }

    fun setGoToFromCurrenciesAction() {
        navManager.navigate(
            ExchangeRateFragmentDirections.actionExchangeRateFragmentToCurrenciesFragment(
                CurrenciesViewType.FROM
            )
        )
    }

    fun setGoToToCurrenciesAction() {
        navManager.navigate(
            ExchangeRateFragmentDirections.actionExchangeRateFragmentToCurrenciesFragment(
                CurrenciesViewType.TO
            )
        )
    }

    // Testing purpose only
    fun setFromCurrency(currency: Currency?) {
        _fromCurrency.postValue(currency)
    }

    // Testing purpose only
    fun setToCurrency(currency: Currency?) {
        _toCurrency.postValue(currency)
    }

    fun initValue() {
        val args = navManager.getArgs() ?: return
        if (args is ExchangeRateFragmentArgs) {
            if (args.type == CurrenciesViewType.FROM) setFromCurrency(args.currency)
            else if (args.type == CurrenciesViewType.TO) setToCurrency(args.currency)
        }
    }
}
