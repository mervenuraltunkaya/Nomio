package com.merve.nomio.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merve.nomio.data.entity.yemek.YemekAciklama

@Database(entities = [YemekAciklama::class], version = 2)
abstract class AciklamaVeritabani: RoomDatabase(){
    abstract fun yemekAciklamaDao(): YemekAciklamaDao
}