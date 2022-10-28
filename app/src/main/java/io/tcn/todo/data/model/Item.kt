package io.tcn.todo.data.model

data class Item(
    val id: Int,
    val name: String,
    val price: Long,
    val quantity: Int,
    val type: Int
)