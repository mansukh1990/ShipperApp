package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.databinding.ItemLayoutAddYourProductBinding
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.Sku


class AddYourProductAdapter(
    private val skuList: MutableList<Pair<Sku, Int>>,
) : RecyclerView.Adapter<AddYourProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutAddYourProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sku: Sku, quantity: Int) {
            binding.txtSkuName.text = sku.name
            binding.txtSkuQty.text = quantity.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLayoutAddYourProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = skuList.size

    fun removeSku(position: Int) {
        skuList.removeAt(position)
        notifyItemRemoved(position)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (sku, quantity) = skuList[position]
        holder.bind(sku, quantity)

    }

}