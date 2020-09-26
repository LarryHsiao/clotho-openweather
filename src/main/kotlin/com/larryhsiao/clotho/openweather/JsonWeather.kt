package com.larryhsiao.clotho.openweather

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.larryhsiao.clotho.openweather.Weather.Type.*

/**
 * Json-parser object of [Weather].
 */
class JsonWeather(private val rawJson: String) : Weather {
    private val json by lazy { JsonParser.parseString(rawJson) }
    override fun temperature(): Double {
        return try {
            mainObj()
                .get("temp")
                .asDouble
        } catch (e: Exception) {
            e.printStackTrace()
            0.0
        }
    }

    override fun humidity(): Float {
        return try {
            mainObj()
                .get("humidity")
                .asFloat
        } catch (e: Exception) {
            e.printStackTrace()
            0f
        }
    }

    override fun type(): Weather.Type {
        return try {
            val type = json.asJsonObject
                .getAsJsonArray("weather")
                .get(0)
                .asJsonObject
                .get("id")
                .asInt
            when (type) {
                in 200..299 -> THUNDERSTORM
                in 300..399 -> DRIZZLE
                in 500..599 -> RAIN
                in 600..699 -> SNOW
                in 700..799 -> ATMOSPHERE
                800 -> CLEAR
                in 801..899 -> CLOUDS
                else -> CLEAR
            }
        } catch (e: Exception) {
            e.printStackTrace()
            CLEAR
        }
    }

    override fun toString(): String {
        return rawJson
    }

    private fun mainObj(): JsonObject {
        return json.asJsonObject
            .getAsJsonObject("main")
    }
}