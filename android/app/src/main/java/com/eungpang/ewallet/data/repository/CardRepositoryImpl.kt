package com.eungpang.ewallet.data.repository

import com.eungpang.ewallet.di.IoDispatcher
import com.eungpang.ewallet.domain.model.Assets
import com.eungpang.ewallet.domain.model.Card
import com.eungpang.ewallet.domain.model.KMoney
import com.eungpang.ewallet.domain.repository.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class MockCardRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : CardRepository {
    val cards = mutableListOf<Assets>().apply {
        add(Card(
            cardName = "DBS Card",
            alias = "Karl's DBS",
            type = "Visa",
            numbers = "1234 1234 1234 5678",
            holderName = "PANKI PARK",
            validYear = "23",
            validMonth = "11",
            cvc = "123"
        ))

        add(Card(
            cardName = "OCBC Card",
            alias = "Karl's OCBC",
            type = "Visa",
            numbers = "5678 5678 5678 1234",
            holderName = "PANKI PARK",
            validYear = "25",
            validMonth = "06",
            cvc = "456"
        ))

        add(Card(
            cardName = "Citi Card",
            alias = "Kyo's CitiBS",
            type = "Master",
            numbers = "4321 4321 4321 4448",
            holderName = "Eunkyo Jeong",
            validYear = "22",
            validMonth = "10",
            cvc = "997"
        ))

        add(KMoney(
            balance = 1297.3f,
            currency = "S$"
        ))
    }

    override suspend fun retrieveCards(limit: Int): Result<List<Assets>> {
        return withContext(coroutineDispatcher) {
            delay(800L)
            Result.success(cards)
        }
    }

    override suspend fun addCard(card: Card): Result<Unit> {
        return withContext(coroutineDispatcher) {
            cards.add(card)
            delay(800L)
            Result.success(Unit)
        }
    }
}