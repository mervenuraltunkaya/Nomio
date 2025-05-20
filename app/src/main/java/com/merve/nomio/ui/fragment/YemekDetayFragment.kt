package com.merve.nomio.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.merve.nomio.databinding.FragmentYemekDetayBinding
import com.merve.nomio.ui.viewmodel.YemekDetayViewModel
import com.merve.nomio.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    private lateinit var binding: FragmentYemekDetayBinding
    private val viewModel: YemekDetayViewModel by viewModels()
    private val args: YemekDetayFragmentArgs by navArgs()
    private var miktar = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYemekDetayBinding.inflate(inflater, container, false)

        val gelenYemek = args.yemek // ✅ sadece bunu kullan

        Log.d("DetayFragment", "NAVARGS ile gelen yemek: ${gelenYemek.yemek_adi}")

        // UI verileri
        binding.detayYemekAdi.text = gelenYemek.yemek_adi
        binding.detayFiyat.text = "${gelenYemek.yemek_fiyat}₺"

        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(requireContext())
            .load(imageUrl)
            .into(binding.detayYemekResim)

        binding.tvMiktar.text = miktar.toString()

        binding.btnArttir.setOnClickListener {
            miktar++
            binding.tvMiktar.text = miktar.toString()
        }

        binding.btnAzalt.setOnClickListener {
            if (miktar > 1) {
                miktar--
                binding.tvMiktar.text = miktar.toString()
            }
        }

        binding.btnSepeteEkle.setOnClickListener {
            lifecycleScope.launch {
                viewModel.sepeteEkle(
                    yemek_adi = gelenYemek.yemek_adi,
                    yemek_resim_adi = gelenYemek.yemek_resim_adi,
                    yemek_fiyat = gelenYemek.yemek_fiyat,
                    yemek_siparis_adet = miktar,
                    kullanici_adi = "merve"
                )
                delay(500)
                Toast.makeText(requireContext(), "${gelenYemek.yemek_adi} sepete eklendi", Toast.LENGTH_SHORT).show()
                Log.d("DetayFragment", "Sepete gönderilen: ${gelenYemek.yemek_adi}")
                val gecisSepet = YemekDetayFragmentDirections.detaySepetGecis()
                findNavController().navigate(gecisSepet)
            }
        }

        binding.iBtnDetayKapat.setOnClickListener {
            Navigation.gecisYap(it, YemekDetayFragmentDirections.detaydanAnasayfaGecis())
        }

        return binding.root
    }
}

