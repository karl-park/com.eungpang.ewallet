package com.eungpang.ewallet.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Routing
 *
 * home/
 *
 */



enum class KWalletScreen(
    val icon: ImageVector,
    val path: String,
    val isMain: Boolean = true
) {
    Home(
        icon = Icons.Filled.Home,
        path = "home"
    ),
    QrCode(
        icon = Icons.Filled.QrCodeScanner,
        path = "qrcode"
    ),
    Account(
        icon = Icons.Filled.Person,
        path = "account"
    ),
    Transaction(
        icon = Icons.Filled.Money,
        isMain = false,
        path = "transaction"
    );

    companion object {
        fun fromRoute(route: String?): KWalletScreen =
            when (route?.substringBefore("/")) {
                Home.path -> Home
                QrCode.path -> QrCode
                Account.path -> Account
                Transaction.path -> Transaction
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}