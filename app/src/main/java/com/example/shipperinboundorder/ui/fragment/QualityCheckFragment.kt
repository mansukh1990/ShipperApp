package com.example.shipperinboundorder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shipperinboundorder.adapter.QualityCheckAdapter
import com.example.shipperinboundorder.databinding.FragmentQualityCheckBinding
import com.example.shipperinboundorder.model.QualityCheckModel
import com.example.shipperinboundorder.model.UploadPod

class QualityCheckFragment : Fragment() {

    private var _binding: FragmentQualityCheckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQualityCheckBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewQualityCheck()

    }

    private fun setRecyclerViewQualityCheck() {
        binding.rvQualityCheck.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = QualityCheckAdapter(addedProductsDetailsList())
        }
    }

    private fun addedProductsDetailsList(): List<QualityCheckModel> {

        val podList = listOf(
            UploadPod(android.R.drawable.ic_menu_upload),
            UploadPod(android.R.drawable.ic_menu_upload),
            UploadPod(android.R.drawable.ic_menu_upload),
            UploadPod(android.R.drawable.ic_menu_upload),
            UploadPod(android.R.drawable.ic_menu_upload),
            UploadPod(android.R.drawable.ic_menu_upload),
        )
        return arrayListOf(
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
            QualityCheckModel(
                "BAC01",
                "Almond 1 kg",
                "8564785646",
                "2313231",
                "25°C to 7O°C",
                false,
                podList
            ),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}