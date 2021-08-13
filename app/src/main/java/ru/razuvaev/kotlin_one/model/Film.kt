package ru.razuvaev.kotlin_one.model

data class Film(
    val adult: Boolean,
    val genres: String,
    val original_language: String,
    val original_title: String,
    val vote_average: String,
    val poster_path: String,
    val overview: String,
    val release_date: String
)