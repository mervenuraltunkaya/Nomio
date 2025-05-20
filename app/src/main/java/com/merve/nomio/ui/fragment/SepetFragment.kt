package com.merve.nomio.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.merve.nomio.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.merve.nomio.databinding.FragmentSepetBinding
import com.merve.nomio.ui.adapter.SepetAdapter
import com.merve.nomio.ui.viewmodel.SepetViewModel
import com.merve.nomio.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private val viewModel: SepetViewModel by viewModels()
    private lateinit var adapter: SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSepetBinding.inflate(inflater, container, false)

        binding.rWSepet.layoutManager = LinearLayoutManager(requireContext())
        adapter = SepetAdapter(requireContext(), emptyList(), viewModel)
        binding.rWSepet.adapter = adapter

        viewModel.sepetYemekler.observe(viewLifecycleOwner) { sepetListesi ->
            Log.d("### OBSERVER", "Yeni liste: $sepetListesi")

            adapter.updateList(sepetListesi) //Liste güncelleniyor

            val toplam = viewModel.toplamFiyatHesapla(sepetListesi)
            binding.tvFiyat.text = "Toplam: ${toplam}₺"

            if (sepetListesi.isEmpty()) {
                binding.tviewSepetBos.visibility = View.VISIBLE
                binding.rWSepet.visibility = View.GONE
            } else {
                binding.tviewSepetBos.visibility = View.GONE
                binding.rWSepet.visibility = View.VISIBLE
            }
        }

        binding.iBtnSepetKapat.setOnClickListener {
            Navigation.gecisYap(it, SepetFragmentDirections.sepettenAnasayfaGecis())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = SepetFragmentDirections.sepettenAnasayfaGecis()
                    findNavController().navigate(action)
                }
            }
        )
    }



    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle("merve")
    }
}
