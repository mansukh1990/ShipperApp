package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.databinding.ItemLayoutDocumentsBinding
import com.example.shipperinboundorder.model.DocumentModel

class DocumentAdapter(private val documentImageList: List<DocumentModel>) :
    RecyclerView.Adapter<DocumentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutDocumentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(documentModel: DocumentModel) {
            binding.imdPod.setImageResource(documentModel.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLayoutDocumentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return documentImageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(documentImageList[position])
    }
}