package com.example.weatherapp.presentation.holders

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.data.extension.dpToPx
import com.example.weatherapp.databinding.ItemDegreesPerHourBinding
import com.facebook.shimmer.ShimmerFrameLayout
import kotlin.properties.Delegates

class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding by viewBinding(ItemDegreesPerHourBinding::bind)

    val itemImage: ImageView = viewBinding.ivItemWeatherStatus
    val itemTextTime: TextView = viewBinding.tvItemTime
    val itemTextTemperature: TextView = viewBinding.tvItemTemperature
    val itemShimmer: ShimmerFrameLayout = viewBinding.itemShimmer

}

class ItemOffsetDecoration(val offset: Int) : RecyclerView.ItemDecoration(){

    private var offsetX by Delegates.notNull<Int>()

    init {
        offsetX = offset
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.left = view.context.dpToPx(32)
                outRect.right = offsetX
            }
            else -> {
                outRect.left = offsetX
                outRect.right = offsetX
            }
        }

    }

}