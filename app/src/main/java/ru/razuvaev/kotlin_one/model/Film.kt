package ru.razuvaev.kotlin_one.model

import java.io.Serializable

 data class Film(
    val adult: Boolean,
    val genres: String,
    val original_language: String,
    val original_title: String,
    val vote_average: String,
    val vote_count: String,
    val poster_path: String,
    val overview: String,
    val release_date: String,
    val budget: String,
    val revenue: String,
    val runTime: String
): Serializable