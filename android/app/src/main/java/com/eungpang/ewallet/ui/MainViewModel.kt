package com.eungpang.ewallet.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eungpang.ewallet.data.repository.MockAuthRepositoryImpl
import com.eungpang.ewallet.domain.model.Card
import com.eungpang.ewallet.domain.model.Transaction
import com.eungpang.ewallet.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val signOutUseCase: SignOutUseCase,

    private val getCardsUseCase: GetCardsUseCase,
    private val addCardUseCase: AddCardUseCase,

    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
) : ViewModel() {
    val isRefreshingHome = MutableStateFlow(false)

    val user = MockAuthRepositoryImpl.mockUser

    val cards = MutableStateFlow<List<Card>>(emptyList())
    val transactions = MutableStateFlow<List<Transaction>>(emptyList())

    init {
        loadHomeScreen()
    }

    fun loadHomeScreen() {
        viewModelScope.launch {
            isRefreshingHome.value = true
            val cardResult = getCardsUseCase.invoke(user)
            val transactionResult = getTransactionsUseCase.invoke()

            if (cardResult.isSuccess) {
                cards.value = cardResult
                    .getOrDefault(listOf())
                    .filterIsInstance(Card::class.java)
            }

            if (transactionResult.isSuccess) {
                transactions.value = transactionResult
                    .getOrDefault(listOf())
            }

            isRefreshingHome.value = false
        }
    }
}