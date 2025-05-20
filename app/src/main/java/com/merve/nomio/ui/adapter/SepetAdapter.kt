package com.merve.nomio.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.merve.nomio.R
import com.merve.nomio.data.entity.sepet.Sepet
import com.merve.nomio.databinding.SepetCardTasarimBinding
import com.merve.nomio.ui.viewmodel.SepetViewModel

class SepetAdapter(
    var mContext: Context,
    var sepetListesi: List<Sepet>,
    var viewModel: SepetViewModel
) : RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>() {

    inner class SepetCardTasarimTutucu(var tasarim: SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val tasarim = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return SepetCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepetYemek = sepetListesi.get(position)
        val t = holder.tasarim

        t.tVSepetYemekAdi.text = sepetYemek.yemek_adi
        t.tvSepetAdet.text = "${sepetYemek.yemek_siparis_adet} adet"
        t.tvSepetFiyat.text = "${sepetYemek.yemek_fiyat} ₺"
        t.tvSepetToplamFiyat.text = "Toplam: ${sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet} ₺"

        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Glide.with(mContext)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error_resmi)
            .into(t.iWSepet)

        t.iBtnSil.setOnClickListener {
            viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id, sepetYemek.kullanici_adi)
        }
    }

    fun updateList(yeniListe: List<Sepet>) {
        sepetListesi = emptyList() // referansı sıfırla
        notifyDataSetChanged()     // tamamen temizle

        sepetListesi = ArrayList(yeniListe) //yeni referansı ata
        notifyDataSetChanged()     // tekrar çiz
    }



    override fun getItemCount(): Int = sepetListesi.size
}
