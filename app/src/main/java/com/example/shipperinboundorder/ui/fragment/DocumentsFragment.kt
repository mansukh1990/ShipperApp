package com.example.shipperinboundorder.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.adapter.DocumentAdapter
import com.example.shipperinboundorder.databinding.FragmentDocumentsBinding
import com.example.shipperinboundorder.model.DocumentModel

class DocumentsFragment : Fragment() {

    private  var _binding : FragmentDocumentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentDocumentsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewDocuments()

    }

    private fun setRecyclerViewDocuments() {
        binding.rvDocuments.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = DocumentAdapter(documentImageList())

        }
    }

    private fun documentImageList(): List<DocumentModel> {
        return arrayListOf(
            DocumentModel(R.drawable.ic_img_document_upload),
            DocumentModel(R.drawable.ic_img_document_upload),
            DocumentModel(R.drawable.ic_img_document_upload),
            DocumentModel(R.drawable.ic_img_document_upload),
            DocumentModel(R.drawable.ic_img_document_upload),
            DocumentModel(R.drawable.ic_img_document_upload),
            DocumentModel(R.drawable.ic_img_document_upload),
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}