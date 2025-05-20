package com.merve.nomio.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merve.nomio.data.entity.sepet.Sepet
import com.merve.nomio.data.repo.SepetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor( val sepetRepository: SepetRepository) : ViewModel() {

    val sepetYemekler = MutableLiveData<List<Sepet>>()

    fun sepetiYukle(kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val liste = try {
                    sepetRepository.sepetiYukle(kullanici_adi)
                } catch (e: Exception) {
                    Log.e("SepetViewModel", "Sunucu cevabı hatalı, boş liste dönülüyor: ${e.message}")
                    emptyList()
                }

                sepetYemekler.value = emptyList() // tetikleme hilesi
                sepetYemekler.value = liste

                Log.d("###ViewModel", "SepetYemekler'e atanan değer: $liste")
            } catch (e: Exception) {
                Log.e("SepetViewModel", "Hata: ${e.message}")
                sepetYemekler.value = emptyList()
            }
        }
    }

    fun sepettenYemekSil(sepet_yemek_id:Int, kullanici_adi: String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cevap = sepetRepository.sepettenYemekSil(sepet_yemek_id, kullanici_adi)

                Log.d("Silme Cevabı", "Success: ${cevap.success}, Message: ${cevap.message}")

                if (cevap.success == 1) {
                    sepetiYukle(kullanici_adi) // yalnızca başarılıysa güncelle
                }

            } catch (e: Exception) {
                Log.e("Silme Hatası", "Silme sırasında hata oluştu: ${e.message}")
            }
        }

    }

    fun toplamFiyatHesapla(sepetLisesi:List<Sepet>):Int{
        return sepetLisesi.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
    }
}
