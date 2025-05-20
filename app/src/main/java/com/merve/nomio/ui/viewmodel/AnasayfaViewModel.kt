package com.merve.nomio.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merve.nomio.data.entity.yemek.Yemekler
import com.merve.nomio.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository):ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    private var tumYemeklerListesi = listOf<Yemekler>()


    init {
        yemekleriYukle()
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val liste = yemeklerRepository.yemekleriYukle()
                Log.d("AnasayfaViewModel", "Gelen veri: $liste")
                tumYemeklerListesi= liste
                yemeklerListesi.value = liste
            }
            catch (e: Exception) {
                Log.e("AnasayfaViewModel", "Hata: ${e.message}")
            }
        }
    }


    fun ara(aramaKelimesi: String) {
        if (aramaKelimesi.isBlank()) {
            yemeklerListesi.value = tumYemeklerListesi
        } else {
            val filtrelenmisListe = tumYemeklerListesi.filter {
                it.yemek_adi.contains(aramaKelimesi, ignoreCase = true)
            }
            yemeklerListesi.value = filtrelenmisListe
        }
    }

}