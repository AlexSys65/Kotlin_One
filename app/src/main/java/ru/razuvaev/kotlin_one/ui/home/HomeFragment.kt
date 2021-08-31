package ru.razuvaev.kotlin_one.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.razuvaev.kotlin_one.model.Film
import ru.razuvaev.myapplication.databinding.FragmentHomeBinding
import java.util.ArrayList


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var textTitleOne: TextView
    private lateinit var textTitleTwo: TextView
    private lateinit var recViewOne: RecyclerView
    private lateinit var recViewTwo: RecyclerView
    lateinit var recyclerViewAdapterOne: RecyclerAdapterHomeOne

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.also {
            textTitleOne = it.textListOne
            textTitleTwo = it.textListTwo
            recViewOne = it.recyclerViewOne
            recViewTwo = it.recyclerViewTwo
        }
        recViewOne.run {
            setHasFixedSize(true)
        }

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getFilmsMutableLiveData().observe(requireActivity(), filmsListUpdateObserver)

        homeViewModel.textOne.observe(viewLifecycleOwner, {
            textTitleOne.text = it
        })
        homeViewModel.textTwo.observe(viewLifecycleOwner, {
            textTitleTwo.text = it
        })
        return root
    }

   /* var filmsListUpdateObserver: Observer<List<Film>> =
        Observer<List<Film>> { filmList ->

            _binding?.recyclerViewOne?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            _binding?.recyclerViewOne?.adapter = RecyclerAdapterHomeOne(filmList)
        }*/

    var filmsListUpdateObserver: Observer<List<Film>> =
        Observer<List<Film>> { filmsArrayList ->
            recyclerViewAdapterOne = RecyclerAdapterHomeOne(filmsArrayList)
            _binding?.recyclerViewOne?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            _binding?.recyclerViewOne?.adapter = recyclerViewAdapterOne
        }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.let {
            _binding = null
        }
    }
}