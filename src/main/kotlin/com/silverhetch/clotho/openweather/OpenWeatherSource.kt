package com.silverhetch.clotho.openweather

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.openweather.Constant.Companion.OPEN_WEATHER_HOST
import com.silverhetch.clotho.stream.StreamString
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Source to fetch weather data from openweather api.
 */
class OpenWeatherSource(
    private val apiKey: String,
    private val lat: Double,
    private val lon: Double
) : Source<Weather> {
    override fun value(): Weather {
        val conn = URL(
            OPEN_WEATHER_HOST + "/weather?lat=${lat}&lon=${lon}&appid=${apiKey}"
        ).openConnection() as HttpURLConnection
        conn.connect()
        if (conn.responseCode == 200) {
            return JsonWeather(StreamString(conn.inputStream).value())
        } else {
            throw RuntimeException(
                "Fetching weather failure: " + StreamString(conn.errorStream).value()
            )
        }
    }
}