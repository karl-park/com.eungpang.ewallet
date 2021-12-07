package com.eungpang.ewallet.domain.model

import androidx.compose.runtime.Immutable

@Immutable
sealed class Assets

@Immutable
data class Card(
    val cardName: String,
    val alias: String?,
    val type: String, // Visa, Master, American Express, ...
    val numbers: String,
    val holderName: String,
    val validYear: String,
    val validMonth: String,
    val cvc: String,
): Assets() {
    val validDateText: String
        get() = "$validMonth $validYear"
}

@Immutable
data class KMoney(
    val balance: Float,
    val currency: String,
): Assets()
