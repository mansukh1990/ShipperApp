package com.example.shipperinboundorder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.databinding.ItemLayoutPutawayBinding
import com.example.shipperinboundorder.model.PutawayModel

class PutawayAdapter(private val putawayList: List<PutawayModel>) :
    RecyclerView.Adapter<PutawayAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutPutawayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return putawayList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(putawayList[position])
    }

    inner class ViewHolder(val binding: ItemLayoutPutawayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(putawayList: PutawayModel) {
            binding.txtBatchCodeValue.text = putawayList.batchCode
            binding.txtSkuNameValue.text = putawayList.skuName
            binding.txtBarcodeValue.text = putawayList.barcode
            binding.txtChamberValue.text = putawayList.chamber
            binding.txtRackValue.text = putawayList.rack
            binding.txtLocationValue.text = putawayList.location
            binding.txtQuantityValue.text = putawayList.quantity


            if (putawayList.expand) {
                binding.clCollapse.visibility = View.VISIBLE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_down)
            } else {
                binding.clCollapse.visibility = View.GONE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_up)
            }
            binding.imgExpandBtn.setOnClickListener {
                putawayList.expand = !putawayList.expand
                notifyDataSetChanged()
            }

        }

    }
}