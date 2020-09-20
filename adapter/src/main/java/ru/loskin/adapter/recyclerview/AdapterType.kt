package ru.loskin.adapter.recyclerview

import java.util.UUID

interface AdapterType {
    fun viewType(): Int = SINGLE_TYPE

    fun key(): Any = this

    companion object {
        const val SINGLE_TYPE = -1

        fun unique() = UUID.randomUUID().hashCode()
    }
}