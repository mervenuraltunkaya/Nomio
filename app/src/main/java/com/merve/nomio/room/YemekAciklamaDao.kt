package com.merve.nomio.room

import androidx.room.Dao
import androidx.room.Query
import com.merve.nomio.data.entity.yemek.YemekAciklama

@Dao
interface YemekAciklamaDao {
    @Query("SELECT * FROM yemek_aciklamalari WHERE yemek_adi= :yemekAdi LIMIT 1")
    suspend fun getAciklama(yemekAdi: String): YemekAciklama?
}