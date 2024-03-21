package com.phanes.phanesbank.extensions

import android.icu.text.NumberFormat

private val currencyFormat by lazy { NumberFormat.getCurrencyInstance() }

fun Number.toCurrency(): String {
    return currencyFormat.format(this)
}