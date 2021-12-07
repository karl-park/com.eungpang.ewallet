package com.eungpang.ewallet.domain.di

import com.eungpang.ewallet.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AuthUseCasesModule {
    @Binds
    fun bindsSignUpUseCase(useCase: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    fun bindsSignInUseCase(useCase: SignInUseCaseImpl): SignInUseCase

    @Binds
    fun bindsSignOutUseCase(useCase: SignOutUseCaseImpl): SignOutUseCase
}
