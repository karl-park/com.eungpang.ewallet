package com.eungpang.ewallet.data.di

import com.eungpang.ewallet.data.repository.AuthManager
import com.eungpang.ewallet.data.repository.AuthManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ManagersModule {
    @Binds
    fun bindsAuthManager(authManager: AuthManagerImpl): AuthManager
}
