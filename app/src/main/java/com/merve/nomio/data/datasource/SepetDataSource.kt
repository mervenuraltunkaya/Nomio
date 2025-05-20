package com.merve.nomio.data.datasource

import android.util.Log
import com.merve.nomio.data.entity.sepet.Sepet
import com.merve.nomio.data.entity.CRUDCevap
import com.merve.nomio.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SepetDataSource (var yemeklerDao: YemeklerDao){

    suspend fun sepetiYukle(kullanici_adi: String): List<Sepet> = withContext(Dispatchers.IO) {
        Log.d("SepetDataSource", "Kullanıcı adı gönderildi: $kullanici_adi")

        val cevap = yemeklerDao.sepetiYukle(kullanici_adi)
        val liste = cevap.sepet_yemekler ?: emptyList() // null kontrolü burada
        Log.d("SepetDataSource", "Gelen liste: ${cevap.sepet_yemekler}")

        return@withContext liste
    }

    suspend fun sepettenYemekSil(sepet_yemek_id:Int, kullanici_adi: String): CRUDCevap {
        return withContext(Dispatchers.IO){
            yemeklerDao.sepettenYemekSil(sepet_yemek_id,kullanici_adi)
        }
    }


}