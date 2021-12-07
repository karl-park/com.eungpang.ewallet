package com.eungpang.ewallet.domain.di

import com.eungpang.ewallet.domain.usecase.AddCardUseCase
import com.eungpang.ewallet.domain.usecase.AddCardUseCaseImpl
import com.eungpang.ewallet.domain.usecase.GetCardsUseCase
import com.eungpang.ewallet.domain.usecase.GetCardsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CardUseCasesModule {
    @Binds
    fun bindsGetCardsUseCase(useCase: GetCardsUseCaseImpl): GetCardsUseCase

    @Binds
    fun bindsAddCardUseCase(useCase: AddCardUseCaseImpl): AddCardUseCase
}
