package ru.loskin.adapter.recyclerview

import androidx.recyclerview.widget.DiffUtil

class DiffUtilItemCallback<T : AdapterType> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.key() == newItem.key()

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}