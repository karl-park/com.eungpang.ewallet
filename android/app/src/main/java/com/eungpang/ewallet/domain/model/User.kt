                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               package com.eungpang.ewallet.domain.model

import androidx.compose.runtime.Immutable
import java.util.*

@Immutable
data class User(
    val uid: String = UUID.randomUUID().toString(),
    val name: String,
    val profileUrl: String?,
    val isPremium: Boolean = false
)
