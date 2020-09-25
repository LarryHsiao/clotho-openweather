package com.silverhetch.clotho.openweather

/**
 * Json-parser object of [Weather].
 */
class JsonWeather(private val rawJson: String) : Weather {
    override fun toString(): String {
        return rawJson
    }
}