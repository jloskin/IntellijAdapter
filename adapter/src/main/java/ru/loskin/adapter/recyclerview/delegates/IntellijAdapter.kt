package ru.loskin.adapter.recyclerview.delegates

import android.view.ViewGroup
import ru.loskin.adapter.recyclerview.AdapterType
import ru.loskin.adapter.recyclerview.BaseRecyclerViewAdapter
import ru.loskin.adapter.recyclerview.BaseRecyclerViewAdapter.BaseViewHolder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class IntellijAdapter<T : AdapterType>(
    map: Map<Int, (ViewGroup) -> BaseViewHolder<out T>>
) : ReadOnlyProperty<Any, BaseRecyclerViewAdapter<T>> {
    constructor(builder: (ViewGroup) -> BaseViewHolder<T>) : this(mapOf(AdapterType.SINGLE_TYPE to builder))

    private val adapter: BaseRecyclerViewAdapter<T> by lazy {
        object : BaseRecyclerViewAdapter<T>(map) {}
    }

    override operator fun getValue(
        thisRef: Any,
        property: KProperty<*>
    ): BaseRecyclerViewAdapter<T> = adapter
}