package com.eungpang.ewallet.domain.repository

import com.eungpang.ewallet.domain.model.Transaction

interface TransactionRepository {
    suspend fun retrieveTransactions(
        limit: Int = 20
    ): Result<List<Transaction>>

    suspend fun addTransaction(
        transaction: Transaction
    ): Result<Unit>
}
