package ru.loskin.intellijadapter

import android.view.ViewGroup
import kotlinx.android.synthetic.main.text_cell_layout.*
import ru.loskin.adapter.recyclerview.AdapterType
import ru.loskin.adapter.recyclerview.BaseRecyclerViewAdapter

class TextViewHolder(
    parent: ViewGroup
) : BaseRecyclerViewAdapter.BaseViewHolder<TextItem>(parent, R.layout.text_cell_layout) {
    override fun bind(item: TextItem) {
        text.text = item.text
    }

    companion object {
        fun creator(): Pair<Int, (ViewGroup) -> TextViewHolder> =
            TextItem.UNIQUE to { parent -> TextViewHolder(parent) }
    }
}

data class TextItem(
    val id: Int,
    val text: String
) : AdapterType {
    override fun key(): Any = id

    override fun viewType(): Int = UNIQUE

    companion object {
        val UNIQUE = AdapterType.unique()
    }
}
