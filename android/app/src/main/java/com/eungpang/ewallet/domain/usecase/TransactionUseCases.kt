package com.eungpang.ewallet.domain.usecase

import com.eungpang.ewallet.domain.model.Transaction
import com.eungpang.ewallet.domain.model.User
import com.eungpang.ewallet.domain.repository.TransactionRepository
import javax.inject.Inject

interface GetTransactionsUseCase {
    suspend fun invoke(): Result<List<Transaction>>
}

class GetTransactionsUseCaseImpl @Inject constructor(
    private val repository: TransactionRepository
) : GetTransactionsUseCase {

    override suspend fun invoke(): Result<List<Transaction>> {
        return repository.retrieveTransactions()
    }
}

interface AddTransactionUseCase {
    suspend fun invoke(
        user: User,
        transaction: Transaction
    ): Result<Unit>
}

class AddTransactionUseCaseImpl @Inject constructor(
    private val repository: TransactionRepository
) : AddTransactionUseCase {
    override suspend fun invoke(user: User, transaction: Transaction): Result<Unit> {
        return repository.addTransaction(transaction)
    }
}
