package com.eungpang.ewallet.domain.usecase

import com.eungpang.ewallet.domain.model.Assets
import com.eungpang.ewallet.domain.model.Card
import com.eungpang.ewallet.domain.model.User
import com.eungpang.ewallet.domain.repository.CardRepository
import javax.inject.Inject

interface GetCardsUseCase {
    suspend fun invoke(user: User): Result<List<Assets>>
}

class GetCardsUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository
) : GetCardsUseCase {

    override suspend fun invoke(user: User): Result<List<Assets>> {
        return cardRepository.retrieveCards()
    }
}

interface AddCardUseCase {
    suspend fun invoke(
        user: User,
        card: Card
    ): Result<Unit>
}

class AddCardUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository
) : AddCardUseCase {
    override suspend fun invoke(user: User, card: Card): Result<Unit> {
        return cardRepository.addCard(card)
    }
}
