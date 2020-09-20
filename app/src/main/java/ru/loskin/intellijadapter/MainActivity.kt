package ru.loskin.intellijadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.loskin.adapter.recyclerview.delegates.IntellijAdapter

class MainActivity : AppCompatActivity() {
    private val intellijAdapter by IntellijAdapter(::TextViewHolder)
    private val intellijAdapterMultiple by IntellijAdapter(
        mapOf(
            TextViewHolder.creator(),
            ImageViewHolder.creator()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = RecyclerView(this).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = intellijAdapterMultiple.apply {
                submitList((0..100).map {
                    if (it % 2 == 0) TextItem(
                        id = it,
                        text = "Text: $it"
                    ) else ImageItem(id = it, resource = R.drawable.ic_android_black_24dp)
                })
            }
        }
        setContentView(recyclerView)
    }
}