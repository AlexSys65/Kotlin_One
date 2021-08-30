package ru.razuvaev.kotlin_one.ui.home


import android.text.TextUtils.substring
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ru.razuvaev.kotlin_one.model.Film
import ru.razuvaev.myapplication.R

class RecyclerAdapterHomeOne(private val listFilm: List<Film>) :
    RecyclerView.Adapter<RecyclerAdapterHomeOne.FilmViewHolder>() {

    private val keyFilm = "film"

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var releaseYear: TextView? = null
        var voteAverage: TextView? = null
        var filmPoster: ImageView?

        init {
            title = itemView.findViewById(R.id.movie_title)
            releaseYear = itemView.findViewById(R.id.film_year_text)
            voteAverage = itemView.findViewById(R.id.film_rating_text)
            filmPoster = itemView.findViewById(R.id.film_icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.title?.text = listFilm[position].original_title
        holder.releaseYear?.text = substring(
            listFilm[position].release_date,
            0,
            4
        ) // TODO не нравится использование чисел. Подумать, как сделать гибче, если другой формат строки
        holder.voteAverage?.text = listFilm[position].vote_average.toString()
        //TODO здесь будет загрузка постера фильма
        holder.itemView.setOnClickListener {
            val bundle = bundleOf(keyFilm to listFilm[position])
            Navigation.findNavController(holder.itemView).navigate(
                R.id.action_navigation_home_to_navigation_detail, bundle
            )

        }
    }

    override fun getItemCount() = listFilm.size
}