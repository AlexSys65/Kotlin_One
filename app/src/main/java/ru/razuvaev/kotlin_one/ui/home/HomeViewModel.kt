package ru.razuvaev.kotlin_one.ui.home

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import ru.razuvaev.kotlin_one.model.Film
import ru.razuvaev.kotlin_one.repositores.FilmLoader


class HomeViewModel : ViewModel(), FillAdapter {

    private val _textOne = MutableLiveData<String>().apply {
        value = "Now Playing"
    }
    private val _recyclerViewOne = MutableLiveData<List<Film>>().apply {
        value = getListFilms("Jack+Reacher")
    }
    private val _textTwo = MutableLiveData<String>().apply {
        value = "Upcoming"
    }
    private val handler = Handler(Looper.getMainLooper())
    var textOne: LiveData<String> = _textOne
    var textTwo: LiveData<String> = _textTwo
    // var recyclerOneAdapter: LiveData<List<Film>> = _recyclerViewOne

    fun getFilmsMutableLiveData(): MutableLiveData<List<Film>> {
        return _recyclerViewOne
    }

    override fun getListFilms(query: String): List<Film> {
        var data: List<Film>? = null
        object : Thread() {
            //Отдельный поток для запроса на сервер
            override fun run() {
                val json: JSONObject? = FilmLoader.getJsonData(query)
                if (json == null) {
                    handler.post {
                        // TODO Возвращаем сообщение об ошибке

                    }
                } else {
                    handler.post {
                        Log.d("Log", "json $json")

                        try {
                            val jsonArray = JSONArray(json)
                            data = mutableListOf()
                            for (i in 0 until jsonArray.length()) {
                                (data as MutableList<Film>).add(i,jsonArray.getJSONObject(i) as Film)
                            }
                        } catch (e: JSONException) {
                            Log.d("Log", "One or more fields not found in the JSON data") // Обработка ошибки
                        }
                    }
                }
            }
        }.start()
        return data
    }
}