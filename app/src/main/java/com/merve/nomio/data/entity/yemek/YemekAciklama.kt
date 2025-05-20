package com.merve.nomio.data.entity.yemek

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "yemek_aciklamalari")
data class YemekAciklama(@PrimaryKey
                         @ColumnInfo(name = "yemek_adi") @NotNull var yemek_adi:String,
                         @ColumnInfo(name = "aciklama") @NotNull var aciklama:String): Serializable {
}