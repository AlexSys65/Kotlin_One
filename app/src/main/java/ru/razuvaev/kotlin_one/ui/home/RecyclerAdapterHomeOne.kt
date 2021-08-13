package ru.razuvaev.kotlin_one.ui.home

import android.content.Context
import android.text.TextUtils.substring
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.razuvaev.kotlin_one.model.Film
import ru.razuvaev.kotlin_one.model.FragmentSendDataListener
import ru.razuvaev.myapplication.R
import java.lang.ClassCastException


class RecyclerAdapterHomeOne(private var context: Context, private val film: List<Film>) :
    RecyclerView.Adapter<RecyclerAdapterHomeOne.FilmViewHolder>() {

    private var mAdapterCallback: FragmentSendDataListener

    init {
        try {
            mAdapterCallback = context as FragmentSendDataListener
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity must implement interface FragmentSendDataListener.")
        }
    }

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        var releaseYear: TextView? = null
        var voteAverage: TextView? = null
        val filmPoster: ImageView?

        init {
            title = itemView.findViewById(R.id.movie_title)
            releaseYear = itemView.findViewById(R.id.film_year_text)
            voteAverage = itemView.findViewById(R.id.film_rating_text)
            filmPoster = itemView.findViewById(R.id.film_icon)

            itemView.setOnClickListener {
                if (bindingAdapterPosition == RecyclerView.NO_POSITION) {
                    return@setOnClickListener
                }
                Toast.makeText(itemView.context , "$bindingAdapterPosition open film " + (title?.text
                    ?: String), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.title?.text = film[position].original_title
        holder.releaseYear?.text = substring(film[position].release_date,0,4) // TODO не нравится использование чисел. Подумать, как сделать гибче, если другой формат строки
        holder.voteAverage?.text = film[position].vote_average
        //TODO здесь будет загрузка постера фильма
    }

    override fun getItemCount() = film.size
}