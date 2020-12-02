package com.larryhsiao.clotho.openweather

import com.larryhsiao.clotho.openweather.Weather.Type.CLOUDS
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Test for [CurrentWeather].
 */
internal class CurrentWeatherTest {
    // language=JSON
    private val sampleData = """{
  "coord": {
    "lon": 120,
    "lat": 23.5
  },
  "weather": [
    {
      "id": 803,
      "main": "Clouds",
      "description": "broken clouds",
      "icon": "04n"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 299.41,
    "feels_like": 296.5,
    "temp_min": 298.15,
    "temp_max": 300.93,
    "pressure": 1011,
    "humidity": 78
  },
  "visibility": 10000,
  "wind": {
    "speed": 10.96,
    "deg": 21
  },
  "clouds": {
    "all": 75
  },
  "dt": 1601138205,
  "sys": {
    "type": 1,
    "id": 7945,
    "country": "TW",
    "sunrise": 1601157015,
    "sunset": 1601200299
  },
  "timezone": 28800,
  "id": 1668347,
  "name": "Taibao",
  "cod": 200
}"""

    @Test
    internal fun checkJsonWeather() {
        val weather: Weather = JsonWeather(sampleData)
        assertEquals(299.41, weather.temperature())
        assertEquals(78f, weather.humidity())
        assertEquals(CLOUDS, weather.type())
    }

    /**
     * Check normal case have regular data.
     */
    @Test
    internal fun checkResponseExist() {
        Assertions.assertNotEquals(
            "",
            CurrentWeather(
                "a5817f5f724548529f05f64e8780ee41", 23.5, 120.0
            ).value().toString()
        )
    }
}