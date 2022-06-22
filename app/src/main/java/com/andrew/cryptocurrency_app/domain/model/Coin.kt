package com.andrew.cryptocurrency_app.domain.model

data class Coin(
    val id: String?,
    val name: String?,
    val symbol: String?,
    val rank: Int?,
    val isActive: Boolean,
)
