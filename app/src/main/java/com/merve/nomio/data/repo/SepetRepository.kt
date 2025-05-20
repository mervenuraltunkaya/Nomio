package com.merve.nomio.data.repo

import com.merve.nomio.data.datasource.SepetDataSource
import com.merve.nomio.data.entity.sepet.Sepet
import com.merve.nomio.data.entity.CRUDCevap

class SepetRepository (var sepetDataSource: SepetDataSource){

    suspend fun sepetiYukle(kullanici_adi: String): List<Sepet> {
        return sepetDataSource.sepetiYukle(kullanici_adi)
    }

    suspend fun sepettenYemekSil(sepet_yemek_id:Int, kullanici_adi: String): CRUDCevap {
        return sepetDataSource.sepettenYemekSil(sepet_yemek_id,kullanici_adi)
    }
}