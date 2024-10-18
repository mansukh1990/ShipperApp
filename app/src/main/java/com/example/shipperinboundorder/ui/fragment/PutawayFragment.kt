package com.example.shipperinboundorder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shipperinboundorder.adapter.PutawayAdapter
import com.example.shipperinboundorder.databinding.FragmentPutawayBinding
import com.example.shipperinboundorder.model.PutawayModel

class PutawayFragment : Fragment() {

    private  var _binding: FragmentPutawayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPutawayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewPutaway()
    }

    private fun setRecyclerViewPutaway() {
        binding.rvPutaway.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PutawayAdapter(putawayList())
        }
    }

    private fun putawayList(): List<PutawayModel> {
        return arrayListOf(
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
            PutawayModel("BAC01", "Almond 1 kg", "BAC01", "2313231", "Chiller Chamber", "Chillrack","Frozloc1",false),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}