package com.example.graduationproject.ui.trip.tablayout.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.BookmarkRecyclerItemBinding
import com.example.graduationproject.model.bookmark.BookmarkModel
import com.squareup.picasso.Picasso

class BookmarkAdapter(
    private val list:  List<BookmarkModel>,
    private val onItemClickHandler: (bookmarkModel: BookmarkModel) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val travelBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.bookmark_recycler_item, parent, false
        )
        return TravelViewHolder(travelBinding)
    }

    class TravelViewHolder(
        private val travelBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(travelBinding.root) {
        private val image by lazy { itemView.findViewById<ImageView>(R.id.bookmark_image) }
        fun onBind(
            bookmarkModel: BookmarkModel,
            onItemClickHandler: (bookmarkModel: BookmarkModel) -> Unit
        ) {
            val binding = travelBinding as BookmarkRecyclerItemBinding
            binding.setVariable(BR.bookModel, bookmarkModel)
            Picasso.get().load(bookmarkModel.images).into(image)
            binding.bookmarkCardView.setOnClickListener { onItemClickHandler(bookmarkModel) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TravelViewHolder).onBind(list[position], onItemClickHandler)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}