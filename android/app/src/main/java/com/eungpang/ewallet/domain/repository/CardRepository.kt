package com.eungpang.ewallet.domain.repository

import com.eungpang.ewallet.domain.model.Assets
import com.eungpang.ewallet.domain.model.Card

interface CardRepository {
    suspend fun retrieveCards(
        limit: Int = 20
    ): Result<List<Assets>>

    suspend fun addCard(
        card: Card
    ): Result<Unit>
}
