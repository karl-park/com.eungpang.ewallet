package com.eungpang.ewallet.domain.model

import androidx.compose.runtime.Immutable
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt

@Immutable
data class Transaction(
    val txId: String = UUID.randomUUID().toString(),
    val txType: TransactionType,
    val uid: String,
    val bank: String,
    val accountNo: String,
    val title: String,
    val description: String,
    val money: Float,
    val currency: String,
    val createdAt: Long // Timestamp in Millis
) {
    companion object {
        const val MAX_LENGTH_DECIMAL_POINT = 2
        const val SIGN_PLUS = "+"
        const val SIGN_MINUS = "-"
        const val SIGN_ZERO = ""
    }

    val moneyText: String
        get() {
            val roundedMoney =
                ((money * 10.toDouble().pow(MAX_LENGTH_DECIMAL_POINT.toDouble()))
                    .roundToInt()).div(10.toDouble().pow(MAX_LENGTH_DECIMAL_POINT.toDouble()))
            return "$currency $roundedMoney"
        }

    val signText: String
        get() = when {
            money < 0 -> SIGN_MINUS
            money > 0 -> SIGN_PLUS
            else -> SIGN_ZERO
        }
}

enum class TransactionType {
    Card,
    KMoney,
}