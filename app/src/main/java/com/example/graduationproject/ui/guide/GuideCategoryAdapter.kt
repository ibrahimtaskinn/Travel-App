package com.example.graduationproject.ui.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.GuideCategoriesItemBinding
import com.example.graduationproject.model.guidecategories.GuideCategoriesModel

class GuideCategoryAdapter(
    private val list:  List<GuideCategoriesModel>,
    private val onItemClickHandler: (GuideCategoriesModel: GuideCategoriesModel) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val travelBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.guide_categories_item,parent,false
        )
        return TravelViewHolder2(travelBinding)
    }

    class TravelViewHolder2(
        private val travelBinding: ViewDataBinding
    ): RecyclerView.ViewHolder(travelBinding.root) {
        fun onBind2(guideCategoriesModel: GuideCategoriesModel, onItemClickHandler: (GuideCategoriesModel: GuideCategoriesModel) -> Unit){
            val binding = travelBinding as GuideCategoriesItemBinding
            binding.setVariable(BR.travelModel2, guideCategoriesModel)
            binding.guideCategoryCardview.setOnClickListener {onItemClickHandler(guideCategoriesModel)}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TravelViewHolder2).onBind2(list[position],onItemClickHandler)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}