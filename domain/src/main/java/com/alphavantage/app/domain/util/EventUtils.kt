package com.alphavantage.app.domain.util

import androidx.lifecycle.MutableLiveData
import com.alphavantage.app.domain.widget.Event

fun <T> MutableLiveData<Event<T>>.postEventValue(data: T) {
    this.postValue(Event(data))
}