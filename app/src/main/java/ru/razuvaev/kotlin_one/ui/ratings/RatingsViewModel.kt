package ru.razuvaev.kotlin_one.ui.ratings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RatingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Press My, please"
    }
    val text: LiveData<String> = _text
}