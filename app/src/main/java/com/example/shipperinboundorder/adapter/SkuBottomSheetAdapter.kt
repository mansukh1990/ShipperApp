package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.databinding.ItemLayoutSkuBinding
import com.example.shipperinboundorder.model.SkuListModel
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.Sku

class SkuBottomSheetAdapter(private val skuList:  MutableList<Pair<Sku, Int>>) :
    RecyclerView.Adapter<SkuBottomSheetAdapter.SkuBottomSheetViewHolder>() {

    class SkuBottomSheetViewHolder(val binding: ItemLayoutSkuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sku: Pair<Sku, Int>) {
            binding.txtSkuName.text = sku.first.name
            binding.txtSkuQty.text = sku.second.toString()

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