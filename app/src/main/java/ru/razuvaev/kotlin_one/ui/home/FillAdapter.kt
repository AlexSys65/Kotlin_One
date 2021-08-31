package ru.razuvaev.kotlin_one.ui.home

import ru.razuvaev.kotlin_one.model.Film

interface FillAdapter {
    fun getListFilms(query: String): List<Film>
}