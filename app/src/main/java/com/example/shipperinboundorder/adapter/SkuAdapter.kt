package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.databinding.ItemLayoutAddYourProductBinding
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.Sku


class SkuAdapter(
    private val skuList: MutableList<Pair<Sku, Int>>,
    private val allowDelete: Boolean,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<SkuAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLayoutAddYourProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sku: Sku, quantity: Int) {
            binding.txtSkuName.text = sku.name
            binding.txtSkuQty.text = quantity.toString()

            if (allowDelete) {
                binding.btnDelete.visibility = View.VISIBLE
                binding.btnDelete.setOnClickListener {
                    onDeleteClick(adapterPosition)
                }
            } else {
                binding.btnDelete.visibility = View.GONE
            }

            binding.btnDelete.setImageResource(R.drawable.icn_delete)


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


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (sku, quantity) = skuList[position]
        holder.bind(sku, quantity)

    }


    fun isEmpty(): Boolean = skuList.isEmpty()

}