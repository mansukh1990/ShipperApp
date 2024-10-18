package com.example.shipperinboundorder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shipperinboundorder.adapter.GrnAdapter
import com.example.shipperinboundorder.databinding.FragmentGRNBinding
import com.example.shipperinboundorder.model.GrnModel
import com.example.shipperinboundorder.model.UploadPod


class GRNFragment : Fragment() {

    private var _binding: FragmentGRNBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGRNBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

    }

    private fun setRecyclerView() {
        binding.rvAddedProductsDetails.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = GrnAdapter(addedProductsDetailsList())
        }
    }

    private fun addedProductsDetailsList(): List<GrnModel> {

        val podList = listOf(
            UploadPod(android.R.drawable.ic_menu_camera),
            UploadPod(android.R.drawable.ic_menu_camera),
            UploadPod(android.R.drawable.ic_menu_camera),
            UploadPod(android.R.drawable.ic_menu_camera),
            UploadPod(android.R.drawable.ic_menu_camera),
            UploadPod(android.R.drawable.ic_menu_camera),
        )
        return arrayListOf(
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
                "25°C to 7O°C",
                false,
                podList
            ),
            GrnModel(
                "ALMN180824",
                "Almond 1 kg",
                "8564785646",
                "129088989",
                "29/11/2024",
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