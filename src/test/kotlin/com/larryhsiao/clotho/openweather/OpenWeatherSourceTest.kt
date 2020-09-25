package com.larryhsiao.clotho.openweather

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Test for [OpenWeatherSource].
 */
internal class OpenWeatherSourceTest {
    /**
     * Check normal case have regular data.
     */
    @Test
    internal fun checkResponseExist() {
        Assertions.assertNotEquals(
            "",
            OpenWeatherSource(
                "a5817f5f724548529f05f64e8780ee41", 23.5, 120.0
            ).value().toString()
        )
    }
}