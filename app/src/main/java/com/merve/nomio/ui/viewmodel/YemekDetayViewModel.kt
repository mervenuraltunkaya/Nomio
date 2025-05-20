package com.merve.nomio.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.merve.nomio.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekDetayViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository): ViewModel() {

     fun sepeteEkle(yemek_adi:String,
                           yemek_resim_adi: String,
                           yemek_fiyat: Int,
                           yemek_siparis_adet: Int,
                           kullanici_adi: String){
        CoroutineScope(Dispatchers.Main).launch{
            try {
                yemeklerRepository.sepeteEkle(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    yemek_siparis_adet,
                    kullanici_adi
                )
                Log.d("YemekDetayViewModel", "Sepete eklendi.")
            } catch (e: Exception) {
                Log.e("YemekDetayViewModel", "Sepete ekleme hatası: ${e.message}")
            }
        }
    }



}