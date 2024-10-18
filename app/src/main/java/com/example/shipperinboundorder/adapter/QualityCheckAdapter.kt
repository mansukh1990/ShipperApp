package com.example.shipperinboundorder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.databinding.ItemLayoutQualityCheckBinding
import com.example.shipperinboundorder.model.QualityCheckModel

class QualityCheckAdapter(private val qualityCheckList: List<QualityCheckModel>) :
RecyclerView.Adapter<QualityCheckAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutQualityCheckBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return qualityCheckList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(qualityCheckList[position])
    }

    inner class ViewHolder(val binding: ItemLayoutQualityCheckBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(qualityCheckList: QualityCheckModel) {
            binding.txtBatchCodeValue.text = qualityCheckList.batchCode
            binding.txtSkuNameValue.text = qualityCheckList.skuName
            binding.txtBarcodeValue.text = qualityCheckList.barcode
            binding.txtDamagedValue.text = qualityCheckList.damagedQty
            binding.txtTemperature.text = qualityCheckList.temp
            if (qualityCheckList.expand) {
                binding.clCollapse.visibility = View.VISIBLE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_down)
            } else {
                binding.clCollapse.visibility = View.GONE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_up)
            }
            binding.imgExpandBtn.setOnClickListener {
                qualityCheckList.expand = !qualityCheckList.expand
                notifyDataSetChanged()
            }
            val uploadPodAdapter = UploadPodAdapter(qualityCheckList.uploadPodList)
            binding.rcPod.apply {
                layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = uploadPodAdapter
            }

        }

    }
}