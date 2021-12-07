package com.eungpang.ewallet.domain.usecase

import com.eungpang.ewallet.domain.model.User
import com.eungpang.ewallet.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface SignUpUseCase {
    suspend fun invoke(
        user: User
    ): Result<Unit>
}

class SignUpUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignUpUseCase {
    override suspend fun invoke(user: User): Result<Unit> {
        return authRepository.signUp(user)
    }
}

interface SignInUseCase {
    suspend fun invoke(
        user: User
    ): Result<Unit>

    suspend fun isLoggedIn() : Boolean
}

class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignInUseCase {
    override suspend fun invoke(user: User): Result<Unit> {
        return authRepository.signIn(user)
    }

    override suspend fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn().first()
    }
}

interface SignOutUseCase {
    suspend fun invoke(
        user: User
    ): Result<Unit>
}

class SignOutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignOutUseCase {
    override suspend fun invoke(user: User): Result<Unit> {
        return authRepository.signOut(user)
    }
}