package ru.razuvaev.kotlin_one.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.razuvaev.kotlin_one.model.Film
import ru.razuvaev.myapplication.databinding.FragmentDetailsFilmBinding

class DetailFragment: Fragment() {

    private lateinit var film: Film

    private var _binding: FragmentDetailsFilmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsFilmBinding.inflate(inflater, container, false)
        film = arguments?.getSerializable("film") as Film
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsMoveTitle.text = film.original_title
        binding.detailsFilmGenres.text = film.genres
        binding.detailsFilmDuration.text = String.format("Film duration: %s min", film.runTime)
        binding.detailsFilmVote.text = String.format("%s (%s) ", film.vote_average, film.vote_count)
        binding.detailsFilmBudget.text = String.format("Budget: %s$", film.budget)
        binding.detailsFilmRevenue.text = String.format("Revenue:  %s$", film.revenue)
        binding.detailsFilmDate.text = String.format("Release date: (%s)", film.release_date)
        binding.detailsFilmOverview.text = film.overview
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}