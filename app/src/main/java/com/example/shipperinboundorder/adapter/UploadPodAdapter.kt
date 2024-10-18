package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.databinding.ItemLayoutPodBinding
import com.example.shipperinboundorder.model.UploadPod

class UploadPodAdapter(
    private val podList: List<UploadPod>
) :
    RecyclerView.Adapter<UploadPodAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemLayoutPodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(uploadPod: UploadPod) {
            binding.imdPod.setImageResource(uploadPod.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutPodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return podList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(podList[position])
        holder.itemView.setOnClickListener {
        }
    }
}