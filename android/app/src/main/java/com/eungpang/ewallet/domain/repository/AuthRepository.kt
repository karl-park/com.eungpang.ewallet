package com.eungpang.ewallet.domain.repository

import com.eungpang.ewallet.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(user: User): Result<Unit>
    suspend fun signIn(user: User): Result<Unit>
    suspend fun signOut(user: User): Result<Unit>

    suspend fun isLoggedIn(): Flow<Boolean>
}
