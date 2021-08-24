package ru.razuvaev.kotlin_one.repositores

import android.util.Log
import org.json.JSONObject
import ru.razuvaev.myapplication.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object FilmLoader {
    private const val OPEN_FILM_API_QUERY =
        "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s&language=ru"
    private const val STATUS_CODE = "page"
    private const val NEW_LINE = "\n"
    private const val CAPACITY = 1024

    fun getJsonData(query: String?): JSONObject? {
        return try {
            val url = URL(
                String.format(
                    OPEN_FILM_API_QUERY,
                    BuildConfig.TMDB_API_KEY,
                    query
                )
            )
            val connection = url.openConnection() as HttpsURLConnection
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val rawData = StringBuilder(CAPACITY)
            var tempVariable: String?
            while (reader.readLine().also { tempVariable = it } != null) {
                rawData.append(tempVariable).append(NEW_LINE)
            }
            reader.close()

            val jsonObject = JSONObject(rawData.toString())
            if (jsonObject.getInt(STATUS_CODE) > 0) { //TODO не понятно, как понять, что запрос прошел успешно, пока сделаем так...
                jsonObject
            } else {
                null
            }
        } catch (e: Exception) {
            Log.d("Log", e.toString())
            null // Обработка ошибки
        }
    }

}