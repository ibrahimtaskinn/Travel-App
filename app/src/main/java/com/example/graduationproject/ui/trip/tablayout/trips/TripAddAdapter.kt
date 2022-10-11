package com.example.graduationproject.ui.trip.tablayout.trips

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.TabTripRecyclerItemBinding
import com.example.graduationproject.model.trip.TripModel
import com.squareup.picasso.Picasso

class TripAddAdapter (
    private val list:  List<TripModel>,
    private val onItemClickHandler: (tripModel: TripModel) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val travelBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.tab_trip_recycler_item, parent, false
        )
        return TravelViewHolder(travelBinding)
    }

    class TravelViewHolder(
        private val travelBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(travelBinding.root) {
        private val image by lazy { itemView.findViewById<ImageView>(R.id.trip_image) }
        fun onBind(
            tripModel: TripModel,
            onItemClickHandler: (tripModel: TripModel) -> Unit
        ) {
            val binding = travelBinding as TabTripRecyclerItemBinding
            binding.setVariable(BR.tripModel, tripModel)
            Picasso.get().load(tripModel.images).into(image)
            binding.tripCardView.setOnClickListener { onItemClickHandler(tripModel) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TravelViewHolder).onBind(list[position], onItemClickHandler)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}