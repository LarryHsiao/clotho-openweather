package com.larryhsiao.clotho.openweather

interface Weather {
    fun type(): Type
    fun temperature(): Double
    fun humidity(): Float

    enum class Type {
        CLEAR,
        CLOUDS,
        DRIZZLE,
        RAIN,
        THUNDERSTORM,
        ATMOSPHERE,
        SNOW
    }
}