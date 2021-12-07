package com.eungpang.ewallet.domain.di

import com.eungpang.ewallet.domain.usecase.AddTransactionUseCase
import com.eungpang.ewallet.domain.usecase.AddTransactionUseCaseImpl
import com.eungpang.ewallet.domain.usecase.GetTransactionsUseCase
import com.eungpang.ewallet.domain.usecase.GetTransactionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface TransactionUseCasesModule {
    @Binds
    fun bindsGetTransactionsUseCase(useCase: GetTransactionsUseCaseImpl): GetTransactionsUseCase

    @Binds
    fun bindsAddTransactionUseCase(useCase: AddTransactionUseCaseImpl): AddTransactionUseCase
}
