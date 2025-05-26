package com.rajedev.comp

import java.util.Locale

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 26/05/25.
 * Description: This will be the data center for the app,
 * providing the respective data for the components.
 */

fun getCountries(): List<String> {
    val isoCountryCodes: Array<String> = Locale.getISOCountries()
    val countriesWithEmojis = arrayListOf<String>()
    for (countryCode in isoCountryCodes) {
        val locale = Locale("", countryCode)
        val countryName: String = locale.displayCountry
        val flagOffset = 0x1F1E6
        val asciiOffset = 0x41
        val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
        val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
        val flag =
            (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
        countriesWithEmojis.add("$countryName $flag")
    }
    return countriesWithEmojis
}
