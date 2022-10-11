package com.example.graduationproject.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.TopDestinationsRecyclerviewItemBinding
import com.example.graduationproject.model.general.GeneralModel
import com.squareup.picasso.Picasso

class TopDestinationsAdapter(
    private val list:  List<GeneralModel>,
    private val onItemClickHandler: (TravelModel: GeneralModel) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val travelBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.top_destinations_recyclerview_item,parent,false
        )
        return TravelViewHolder(travelBinding)
    }

    class TravelViewHolder(
        private val TravelBinding: ViewDataBinding
    ): RecyclerView.ViewHolder(TravelBinding.root) {
        private val image by lazy { itemView.findViewById<ImageView>(R.id.tob_destinations_image)}
        fun onBind(TravelModel: GeneralModel, onItemClickHandler: (TravelModel: GeneralModel) -> Unit){
            val binding = TravelBinding as TopDestinationsRecyclerviewItemBinding
            binding.setVariable(BR.travelModel, TravelModel)
            Picasso.get().load(TravelModel.images[0].url).into(image)
            binding.topDestinationsCardView.setOnClickListener {onItemClickHandler(TravelModel)}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TravelViewHolder).onBind(list[position],onItemClickHandler)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}