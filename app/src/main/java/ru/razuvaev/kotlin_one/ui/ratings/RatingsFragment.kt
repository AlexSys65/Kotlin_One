package ru.razuvaev.kotlin_one.ui.ratings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.razuvaev.myapplication.R
import ru.razuvaev.myapplication.databinding.FragmentRatingsBinding

class RatingsFragment : Fragment() {

    private lateinit var ratingsViewModel: RatingsViewModel
    private var _binding: FragmentRatingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ratingsViewModel =
            ViewModelProvider(this).get(RatingsViewModel::class.java)

        _binding = FragmentRatingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button: AppCompatButton = binding.btnRatings
        ratingsViewModel.text.observe(viewLifecycleOwner, {
            button.text = it
        })
        button.setOnClickListener {
            Snackbar.make(it, resources.getString(R.string.example_snack_bar), Snackbar.LENGTH_LONG).show()
        }
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}