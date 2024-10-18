package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.databinding.ItemLayoutSkuBinding
import com.example.shipperinboundorder.model.SkuListModel

class SkuBottomSheetAdapter(private val skuList: List<SkuListModel>) :
    RecyclerView.Adapter<SkuBottomSheetAdapter.SkuBottomSheetViewHolder>() {

    class SkuBottomSheetViewHolder(val binding: ItemLayoutSkuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(skuListModel: SkuListModel) {
            binding.txtSkuName.text = skuListModel.skuName
            binding.txtSkuQty.text = skuListModel.skuQty

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkuBottomSheetViewHolder {
        val binding =
            ItemLayoutSkuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkuBottomSheetViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return skuList.size
    }

    override fun onBindViewHolder(holder: SkuBottomSheetViewHolder, position: Int) {
        holder.bind(skuList[position])
    }
}