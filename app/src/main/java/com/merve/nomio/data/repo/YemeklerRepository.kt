package com.merve.nomio.data.repo

import com.merve.nomio.data.datasource.YemeklerDataSource
import com.merve.nomio.data.entity.yemek.Yemekler

class YemeklerRepository(var yemeklerDataSource: YemeklerDataSource) {

    suspend fun yemekleriYukle():List<Yemekler> = yemeklerDataSource.yemekleriYukle()

    suspend fun sepeteEkle(yemek_adi: String,
                           yemek_resim_adi: String,
                           yemek_fiyat: Int,
                           yemek_siparis_adet: Int,
                           kullanici_adi: String){
        yemeklerDataSource.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }


}