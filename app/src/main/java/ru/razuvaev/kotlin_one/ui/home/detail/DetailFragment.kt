package ru.razuvaev.kotlin_one.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.razuvaev.kotlin_one.model.Film
import ru.razuvaev.myapplication.databinding.FragmentDetailsFilmBinding

class DetailFragment : Fragment() {

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
        with(film) {
            binding.also {
                it.detailsMoveTitle.text = original_title
                it.detailsFilmGenres.text = genres
                it.detailsFilmDuration.text = String.format("Film duration: %s min", runTime)
                it.detailsFilmVote.text =
                    String.format("%s (%s) ", vote_average, vote_count)
                it.detailsFilmBudget.text = String.format("Budget: %s$", budget)
                it.detailsFilmRevenue.text = String.format("Revenue:  %s$", revenue)
                it.detailsFilmDate.text = String.format("Release date: (%s)", release_date)
                it.detailsFilmOverview.text = overview
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.let {
            _binding = null
        }
    }
}