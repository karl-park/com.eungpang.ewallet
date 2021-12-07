package com.eungpang.ewallet.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.eungpang.ewallet.di.IoDispatcher
import com.eungpang.ewallet.domain.model.User
import com.eungpang.ewallet.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class MockAuthRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val authManager: AuthManager,
) : AuthRepository {
    companion object {
        val mockUser = User(
            name = "Karl",
            profileUrl = "https://lh3.googleusercontent.com/ogw/ADea4I4yPRwIQbOmapN2hm1hA199DkJpXuzYRpX9Qhaciw=s64-c-mo",
            isPremium = true
        )
    }

    override suspend fun isLoggedIn(): Flow<Boolean> = authManager.isUserLoggedIn

    override suspend fun signUp(user: User): Result<Unit> {
        return withContext(coroutineDispatcher) {
            delay(800L)
            val mockToken = "1234-5678-abcd-efgh"
            authManager.setUserInfo(
                token = mockToken,
                userName = user.name
            )
            Result.success(Unit)
        }
    }

    override suspend fun signIn(user: User): Result<Unit> {
        return withContext(coroutineDispatcher) {
            delay(800L)
            val mockToken = "1234-5678-abcd-efgh"
            authManager.setUserInfo(
                token = mockToken,
                userName = user.name
            )
            Result.success(Unit)
        }
    }

    override suspend fun signOut(user: User): Result<Unit> {
        return withContext(coroutineDispatcher) {
            delay(800L)
            authManager.clearUserInfo()
            Result.success(Unit)
        }
    }
}

interface AuthManager {
    val userToken: Flow<String>
    val userName: Flow<String>
    val isUserLoggedIn: Flow<Boolean>

    suspend fun setUserInfo(token: String, userName: String)
    suspend fun clearUserInfo()
}

class AuthManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : AuthManager {
    companion object {
        private const val PREFERENCE_NAME = "PREF_KWALLET"
        private val PREF_KEY_AUTH_TOKEN = stringPreferencesKey("key_auth_token")
        private val PREF_KEY_USER_NAME = stringPreferencesKey("key_user_name")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCE_NAME
    )

    override val userToken: Flow<String>
        get() = context.dataStore.data.map {
            it[PREF_KEY_AUTH_TOKEN] ?: ""
        }

    override val userName: Flow<String>
        get() = context.dataStore.data.map {
            it[PREF_KEY_USER_NAME] ?: ""
        }

    override val isUserLoggedIn: Flow<Boolean>
        get() = userToken.combine(userName) { token, name ->
            isValidToken(token, name)
        }

    override suspend fun setUserInfo(token: String, userName: String) {
        context.dataStore.edit {
            it[PREF_KEY_AUTH_TOKEN] = token
            it[PREF_KEY_USER_NAME] = userName
        }
    }

    override suspend fun clearUserInfo() {
        context.dataStore.edit {
            it[PREF_KEY_AUTH_TOKEN] = ""
            it[PREF_KEY_USER_NAME] = ""
        }
    }

    private suspend fun isValidToken(token: String, userName: String) : Boolean {
        delay(300L)
        if (userName.isBlank() || token.isBlank()) return false
        return true
    }
}
