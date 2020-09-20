package ru.loskin.intellijadapter

import android.view.ViewGroup
import androidx.annotation.DrawableRes
import kotlinx.android.synthetic.main.image_cell_layout.*
import kotlinx.android.synthetic.main.text_cell_layout.*
import kotlinx.android.synthetic.main.text_cell_layout.text
import ru.loskin.adapter.recyclerview.AdapterType
import ru.loskin.adapter.recyclerview.BaseRecyclerViewAdapter

class ImageViewHolder(
    parent: ViewGroup
) : BaseRecyclerViewAdapter.BaseViewHolder<ImageItem>(parent, R.layout.image_cell_layout) {
    override fun bind(item: ImageItem) {
        image.setImageResource(item.resource)
    }

    companion object {
        fun creator(): Pair<Int, (ViewGroup) -> ImageViewHolder> =
            ImageItem.UNIQUE to { parent -> ImageViewHolder(parent) }
    }
}

data class ImageItem(
    val id: Int,
    @DrawableRes val resource: Int
) : AdapterType {
    override fun key(): Any = id

    override fun viewType(): Int = UNIQUE

    companion object {
        val UNIQUE = AdapterType.unique()
    }
}
