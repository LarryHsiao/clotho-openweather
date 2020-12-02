package com.larryhsiao.clotho.openweather

import com.larryhsiao.clotho.openweather.Constant.Companion.OPEN_WEATHER_HOST
import com.larryhsiao.clotho.Source
import com.larryhsiao.clotho.stream.StreamString
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Source to fetch weather data from openweather api.
 */
class CurrentWeather(
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