package ru.loskin.adapter.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import ru.loskin.adapter.recyclerview.AdapterType.Companion.SINGLE_TYPE
import ru.loskin.adapter.recyclerview.BaseRecyclerViewAdapter.BaseViewHolder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

abstract class BaseRecyclerViewAdapter<T : AdapterType>(
    private val genMap: Map<Int, (ViewGroup) -> BaseViewHolder<out T>>
) : ListAdapter<T, BaseViewHolder<T>>(DiffUtilItemCallback<T>()) {

    constructor(simple: (ViewGroup) -> BaseViewHolder<out T>) : this(mapOf(SINGLE_TYPE to simple))

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        genMap[viewType]?.invoke(parent) as? BaseViewHolder<T> ?: throw IllegalArgumentException()

    override fun getItemViewType(position: Int): Int =
        if (genMap.size == 1) SINGLE_TYPE else getItem(position).viewType()

    override fun onViewRecycled(holder: BaseViewHolder<T>) {
        super.onViewRecycled(holder)
        holder.recycled()
    }

    override fun getItemId(position: Int): Long = getItem(position).key().hashCode().toLong()

    abstract class BaseViewHolder<T : AdapterType>(
        parent: ViewGroup,
        @LayoutRes layout: Int,
        override val containerView: View = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        protected val context: Context = itemView.context

        protected fun uiThread(func: () -> Unit) {
            itemView.post(func)
        }

        protected fun ioThread(func: () -> Unit) {
            executor.submit(func)
        }

        open fun bind(item: T) {}

        open fun recycled() {}

        companion object {
            val executor: ExecutorService = Executors.newFixedThreadPool(9)
        }
    }
}

