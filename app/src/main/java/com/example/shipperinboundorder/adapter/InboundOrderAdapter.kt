package com.example.shipperinboundorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.databinding.ItemLayoutWarehouseOrdersBinding
import com.example.shipperinboundorder.model.Status.ARRIVED
import com.example.shipperinboundorder.model.Status.DISPATCH
import com.example.shipperinboundorder.model.Status.DISPATCHCOMPLETED
import com.example.shipperinboundorder.model.Status.GRNCOMPLETED
import com.example.shipperinboundorder.model.Status.GRNINPROCESS
import com.example.shipperinboundorder.model.Status.GRNPENDING
import com.example.shipperinboundorder.model.Status.MANIFEST
import com.example.shipperinboundorder.model.Status.ORDERPLACED
import com.example.shipperinboundorder.model.Status.PICKING
import com.example.shipperinboundorder.model.Status.PICKINGCOMPLETED
import com.example.shipperinboundorder.model.Status.PUTAWAYCOMPLETED
import com.example.shipperinboundorder.model.Status.PUTAWAYINPROCESS
import com.example.shipperinboundorder.model.Status.PUTAWAYPENDING
import com.example.shipperinboundorder.model.Status.QCCOMPLETED
import com.example.shipperinboundorder.model.Status.QCINPROCESS
import com.example.shipperinboundorder.model.Status.QCPENDING
import com.example.shipperinboundorder.model.Status.RETURN
import com.example.shipperinboundorder.model.Status.RETURNCOMPLETED
import com.example.shipperinboundorder.model.Status.RETURNORDER
import com.example.shipperinboundorder.model.modelapi.orderlist.Order

class InboundOrderAdapter(
    private val orderList: List<Order>,
) : RecyclerView.Adapter<InboundOrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: ItemLayoutWarehouseOrdersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemLayoutWarehouseOrdersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.apply {
            binding.txtIdCustValue.text = order.order_no
            binding.txtCustomerStatus.text = order.order_type
            binding.txtCreatedByValue.text = order.created_by_type
            binding.txtExpectedDateValue.text = order.expected_delivery_date

            when (order.order_type) {
                "Inbound" -> {
                    binding.imgInbound.visibility = ViewGroup.VISIBLE
                    binding.txtCustomerStatus.text =
                        ContextCompat.getString(binding.root.context, R.string.str_inbound)
                }

                "Outbound" -> {
                    binding.imgInbound.visibility = ViewGroup.GONE
                    binding.txtCustomerStatus.text =
                        ContextCompat.getString(binding.root.context, R.string.str_outbound)
                    binding.imgInbound.setImageResource(R.drawable.img_outbound)

                }

            }
            when (order.status_name) {
                ORDERPLACED.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_order_placed
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_order_placed
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_order_placed
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                ARRIVED.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(binding.root.context, R.string.str_arrived)
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_order_arrived
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_arrived
                    )
                    binding.txtCreatedByValue.text = order.created_by_type

                }

                GRNPENDING.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_grn_pending
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_grn_pending,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_grn_pending
                    )
                    binding.txtCreatedByValue.text = order.created_by_type

                }

                GRNINPROCESS.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_grn_in_process
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_grn_in_process
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_grn_in_process,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_grn_in_process
                    )
                    binding.txtCreatedByValue.text = order.created_by_type

                }

                GRNCOMPLETED.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_grn_completed
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check_white,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_grn_completed
                    )
                    binding.txtCreatedByValue.text = order.created_by_type

                }

                QCPENDING.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_qc_pending
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_grn_pending,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_qc_pending
                    )
                    binding.txtCreatedByValue.text = order.created_by_type

                }

                QCINPROCESS.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_qc_in_process
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_qc_in_process
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_process,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_qc_process
                    )
                    binding.txtCreatedByValue.text = order.created_by_type

                }

                QCCOMPLETED.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_qc_completed
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check_white,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_qc_completed
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                PUTAWAYPENDING.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_putaway_pending
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_grn_pending,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_qc_pending
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                PUTAWAYINPROCESS.toString() -> {
                    binding.btnStatus.text = ContextCompat.getString(
                        binding.root.context,
                        R.string.str_putaway_in_process
                    )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_qc_in_process
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_process,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_puyayay_in_process
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                PUTAWAYCOMPLETED.toString() -> {
                    binding.btnStatus.text = ContextCompat.getString(
                        binding.root.context,
                        R.string.str_putaway_completed
                    )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check_white,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_qc_completed
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                DISPATCHCOMPLETED.toString() -> {
                    binding.btnStatus.text = ContextCompat.getString(
                        binding.root.context,
                        R.string.str_dispatch_completed
                    )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check_white,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_qc_completed
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                RETURN.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(binding.root.context, R.string.str_return)
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_return
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_return,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_return
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                RETURNORDER.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_return_order
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_return
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_return,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_return
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                MANIFEST.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(binding.root.context, R.string.str_manifest)
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_manifest
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_process,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_manifest
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                DISPATCH.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(binding.root.context, R.string.str_dispatch)
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_manifest
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_process,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_manifest
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                PICKING.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(binding.root.context, R.string.str_picking)
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_manifest
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_process,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_manifest
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                PICKINGCOMPLETED.toString() -> {
                    binding.btnStatus.text = ContextCompat.getString(
                        binding.root.context,
                        R.string.str_picking_completed
                    )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check_white,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_qc_completed
                    )
                    binding.txtCreatedByValue.text = order.created_by_type
                }

                RETURNCOMPLETED.toString() -> {
                    binding.btnStatus.text =
                        ContextCompat.getString(
                            binding.root.context,
                            R.string.str_return_completed
                        )
                    binding.btnStatus.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.clr_text_return
                        )
                    )
                    binding.btnStatus.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_return,
                        0,
                        0,
                        0
                    )
                    binding.btnStatus.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_return
                    )
                    binding.txtCreatedByValue.text = order.created_by_type

                }
            }

        }

    }

}

