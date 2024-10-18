package com.example.shipperinboundorder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.databinding.ItemLayoutStatusLogBinding
import com.example.shipperinboundorder.model.Status
import com.example.shipperinboundorder.model.StatusLogModel

class StatusLogAdapter(private val statusLogList: List<StatusLogModel>) :
    RecyclerView.Adapter<StatusLogAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutStatusLogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return statusLogList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(statusLogList[position])
    }

    inner class ViewHolder(val binding: ItemLayoutStatusLogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged", "ResourceAsColor")
        fun bind(statusLogList: StatusLogModel) {
            binding.txtStatusByValue.text = statusLogList.statusBy
            binding.txtTimeStampValue.text = statusLogList.timeStamp
            binding.txtRemarksValue.text = statusLogList.remarks

            when (statusLogList.orderStatus) {

                Status.ORDERPLACED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_order_placed)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_order_placed))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_order_placed)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.ARRIVED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_arrived)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_order_arrived))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_arrived)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"

                }

                Status.GRNPENDING -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_grn_pending)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_grn_pending, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_grn_pending)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"

                }

                Status.GRNINPROCESS -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_grn_in_process)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_grn_in_process))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_grn_in_process, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_grn_in_process)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"

                }

                Status.GRNCOMPLETED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_grn_completed)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_white, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_grn_completed)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"

                }

                Status.QCPENDING -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_qc_pending)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_grn_pending, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_qc_pending)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"

                }

                Status.QCINPROCESS -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_qc_in_process)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_qc_in_process))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_process, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_qc_process)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"

                }

                Status.QCCOMPLETED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_qc_completed)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_white, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_qc_completed)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.PUTAWAYPENDING -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_putaway_pending)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_grn_pending, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_qc_pending)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.PUTAWAYINPROCESS -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_putaway_in_process)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_qc_in_process))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_process, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_puyayay_in_process)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.PUTAWAYCOMPLETED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_putaway_completed)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_white, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_qc_completed)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.DISPATCHCOMPLETED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_dispatch_completed)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_white, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_qc_completed)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.RETURN -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_return)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_return))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_return, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_return)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.RETURNORDER -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_return_order)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_return))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_return, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_return)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.MANIFEST -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_manifest)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_manifest))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_process, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_manifest)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.DISPATCH -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_dispatch)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_manifest))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_process, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_manifest)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.PICKING -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_picking)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_manifest))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_process, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_manifest)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.PICKINGCOMPLETED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_picking_completed)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_white, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_qc_completed)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"
                }

                Status.RETURNCOMPLETED -> {
                    binding.txtStatus.text = ContextCompat.getString(binding.root.context, R.string.str_return_completed)
                    binding.txtStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.clr_text_return))
                    binding.txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_return, 0, 0, 0)
                    binding.txtStatus.background = ContextCompat.getDrawable(binding.root.context,R.drawable.bg_return)
                    binding.txtStatusByValue.text = "Punit Sonagra - Worker"

                }
            }

            if (statusLogList.expand) {
                binding.clCollapse.visibility = View.VISIBLE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_down)
            } else {
                binding.clCollapse.visibility = View.GONE
                binding.imgExpandBtn.setImageResource(R.drawable.btn_up)
            }
            binding.imgExpandBtn.setOnClickListener {
                statusLogList.expand = !statusLogList.expand
                notifyDataSetChanged()
            }

        }

    }
}