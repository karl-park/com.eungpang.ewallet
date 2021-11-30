package com.eungpang.ewallet.domain.model

import java.util.*
import javax.annotation.concurrent.Immutable

@Immutable
data class User(
    val uid: String = UUID.randomUUID().toString(),
    val name: String,
    val profileUrl: String?,
    val isPremium: Boolean = false
)
