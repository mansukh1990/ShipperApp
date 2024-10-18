package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.databinding.ItemLayoutSkuBinding
import com.example.shipperinboundorder.model.SkuListModel

class SkuAdapter(private val skuList: List<SkuListModel>) :
    RecyclerView.Adapter<SkuAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutSkuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(skuListModel: SkuListModel) {
            binding.txtSkuName.text = skuListModel.skuName
            binding.txtSkuQty.text = skuListModel.skuQty

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLayoutSkuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return skuList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(skuList[position])
    }
}