package ru.razuvaev.kotlin_one.ui.ratings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RatingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Ratings Fragment"
    }
    val text: LiveData<String> = _text
}