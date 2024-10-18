package com.example.shipperinboundorder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shipperinboundorder.adapter.StatusLogAdapter
import com.example.shipperinboundorder.databinding.FragmentStatusLogBinding
import com.example.shipperinboundorder.model.Status
import com.example.shipperinboundorder.model.StatusLogModel

class StatusLogFragment : Fragment() {

    private var _binding: FragmentStatusLogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatusLogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewStatusLog()
    }

    private fun setRecyclerViewStatusLog() {
        binding.rvStatusLog.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = StatusLogAdapter(statusLogList())
        }
    }

    private fun statusLogList(): List<StatusLogModel> {
        return arrayListOf(
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.ORDERPLACED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.ARRIVED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.GRNPENDING,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.GRNINPROCESS,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.GRNCOMPLETED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.QCPENDING,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.QCINPROCESS,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.QCCOMPLETED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.PUTAWAYPENDING,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.PUTAWAYINPROCESS,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.PUTAWAYCOMPLETED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.DISPATCHCOMPLETED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.RETURN,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.RETURNORDER,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.MANIFEST,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.DISPATCH,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.PICKING,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.PICKINGCOMPLETED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
            StatusLogModel(
                "Punit Sonagra - Worker",
                Status.RETURNCOMPLETED,
                "21/08/2024 16:13:43",
                "N/A",
                false
            ),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}