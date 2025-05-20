package com.merve.nomio.data.datasource

import android.util.Log
import com.merve.nomio.data.entity.sepet.SepetCevap
import com.merve.nomio.data.entity.yemek.Yemekler
import com.merve.nomio.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource (var yemeklerDao: YemeklerDao) {

    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO){
        return@withContext yemeklerDao.yemekleriYukle().yemekler
    }

    /*suspend fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        withContext(Dispatchers.IO){
            yemeklerDao.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
    }*/

    suspend fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        try {
            val sepetCevap = try {
                yemeklerDao.sepetiYukle(kullanici_adi)
            } catch (e: Exception) {
                null
            }

            val mevcutlar = sepetCevap?.sepet_yemekler ?: emptyList()
            val ayniUrun = mevcutlar.find { it.yemek_adi == yemek_adi }

            if (ayniUrun != null) {
                // varsa sil, toplam adet ile yeniden ekle
                yemeklerDao.sepettenYemekSil(ayniUrun.sepet_yemek_id, kullanici_adi)
                val yeniAdet = ayniUrun.yemek_siparis_adet + yemek_siparis_adet
                yemeklerDao.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yeniAdet, kullanici_adi)
            } else {
                // yoksa doğrudan ekle
                yemeklerDao.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            }
        } catch (e: Exception) {
            Log.e("SepeteEkle", "Hata: ${e.message}")
        }
    }


}