package ru.razuvaev.kotlin_one.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.razuvaev.kotlin_one.model.Film

class HomeViewModel : ViewModel(), FillAdapter {

    private val _textOne = MutableLiveData<String>().apply {
        value = "Now Playing"
    }
    private val _recyclerViewOne = MutableLiveData<List<Film>>().apply {
        value = getListFilms()
    }
    private val _textTwo = MutableLiveData<String>().apply {
        value = "Upcoming"
    }
    var textOne: LiveData<String> = _textOne
    var textTwo: LiveData<String> = _textTwo
   // var recyclerOneAdapter: LiveData<List<Film>> = _recyclerViewOne

    fun getFilmsMutableLiveData(): MutableLiveData<List<Film>> {
        return _recyclerViewOne
    }

    override fun getListFilms(): List<Film> {
        var data = mutableListOf<Film>()
        (0..5).forEach { _ -> data.add(Film(false,
            "Comedy", "EN", "Fight Club", "8.4", "22183",
            "/8kNruSfhk5IoE4eZOc4UpvDn6tq.jpg", " ticking-time-bomb insomniac and a slippery soap ...",
            "1999-10-15", "63000000", "100853753", "139")) }
        (6..10).forEach { _ -> data.add(Film(false,
            "Drama", "EN", "Второй фильм", "8.4", "22183",
            "/8kNruSfhk5IoE4eZOc4UpvDn6tq.jpg", " ticking-time-bomb insomniac and a slippery soap ...",
            "1999-10-15", "63000000", "100853753", "139")) }
        (11..16).forEach { _ -> data.add(Film(false,
            "Detective", "EN", "Третий фильм", "8.4", "22183",
            "/8kNruSfhk5IoE4eZOc4UpvDn6tq.jpg", " ticking-time-bomb insomniac and a slippery soap ...",
            "1999-10-15", "63000000", "100853753", "139")) }
        return data
    }
}