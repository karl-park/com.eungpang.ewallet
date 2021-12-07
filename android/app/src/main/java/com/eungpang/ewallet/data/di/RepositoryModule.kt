package com.eungpang.ewallet.data.di

import com.eungpang.ewallet.data.repository.MockAuthRepositoryImpl
import com.eungpang.ewallet.data.repository.MockCardRepositoryImpl
import com.eungpang.ewallet.data.repository.MockTransactionRepositoryImpl
import com.eungpang.ewallet.domain.repository.AuthRepository
import com.eungpang.ewallet.domain.repository.CardRepository
import com.eungpang.ewallet.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAuthRepository(repository: MockAuthRepositoryImpl): AuthRepository

    @Binds
    fun bindsCardRepository(repository: MockCardRepositoryImpl): CardRepository

    @Binds
    fun bindsTransactionRepository(repository: MockTransactionRepositoryImpl): TransactionRepository
}
