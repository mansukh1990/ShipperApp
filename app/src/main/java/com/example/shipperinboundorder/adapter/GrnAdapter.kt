package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.databinding.ItemLayoutGrnBinding
import com.example.shipperinboundorder.model.GrnModel

class GrnAdapter(private val grnList: List<GrnModel>) :
    RecyclerView.Adapter<GrnAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemLayoutGrnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(grnDetails: GrnModel) {
            binding.txtBatchCodeValue.text = grnDetails.batchCode
            binding.txtSkuNameValue.text = grnDetails.skuName
            binding.txtBarcodeValue.text = grnDetails.barcode
            binding.txtQuantityValue.text = grnDetails.quantity
            binding.txtExpiryDateValue.text = grnDetails.expiryDate
            binding.txtTemperature.text = grnDetails.temp
            if (grnDetails.expand) {
                binding.clCollapse.visibility = View.VISIBLE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_down)
            } else {
                binding.clCollapse.visibility = View.GONE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_up)
            }
            binding.imgExpandBtn.setOnClickListener {
                grnDetails.expand = !grnDetails.expand
                notifyDataSetChanged()
            }
            val uploadPodAdapter = UploadPodAdapter(grnDetails.uploadPodList)
            binding.rcPod.apply {
                layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = uploadPodAdapter
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLayoutGrnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return grnList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(grnList[position])
    }
}