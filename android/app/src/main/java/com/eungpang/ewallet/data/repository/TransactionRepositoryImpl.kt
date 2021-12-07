package com.eungpang.ewallet.data.repository

import com.eungpang.ewallet.di.IoDispatcher
import com.eungpang.ewallet.domain.model.Transaction
import com.eungpang.ewallet.domain.model.TransactionType
import com.eungpang.ewallet.domain.repository.TransactionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MockTransactionRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : TransactionRepository {

    private val transactions = mutableListOf<Transaction>().apply {
        add(Transaction(
            txType = TransactionType.Card,
            uid = "00001",
            bank = "DBS",
            accountNo = "1234 1234 1234 5678",
            title = "JustCo MarinaOne",
            description = "",
            currency = "S$",
            money = -1200f,
            createdAt = 1638784974974
        ))

        add(Transaction(
            txType = TransactionType.Card,
            uid = "00001",
            bank = "DBS",
            accountNo = "1234 1234 1234 5678",
            title = "KFood1",
            description = "",
            currency = "S$",
            money = 35.50f,
            createdAt = 1638784914974
        ))

        add(Transaction(
            txType = TransactionType.Card,
            uid = "00001",
            bank = "DBS",
            accountNo = "1234 1234 1234 5678",
            title = "KFood2",
            description = "",
            currency = "S$",
            money = 185.50f,
            createdAt = 1638784414974
        ))

        add(Transaction(
            txType = TransactionType.Card,
            uid = "00001",
            bank = "DBS",
            accountNo = "1234 1234 1234 5678",
            title = "KFood3",
            description = "",
            currency = "S$",
            money = 29.70f,
            createdAt = 1638782914974
        ))
    }

    override suspend fun retrieveTransactions(limit: Int): Result<List<Transaction>> {
        return withContext(coroutineDispatcher) {
            delay(800L)
            Result.success(transactions)
        }
    }

    override suspend fun addTransaction(transaction: Transaction): Result<Unit> {
        return withContext(coroutineDispatcher) {
            transactions.add(transaction)
            delay(800L)
            Result.success(Unit)
        }
    }
}