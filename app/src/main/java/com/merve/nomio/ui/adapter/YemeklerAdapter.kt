package com.merve.nomio.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.merve.nomio.R
import com.merve.nomio.data.entity.yemek.Yemekler
import com.merve.nomio.databinding.YemekCardTasarimBinding
import com.merve.nomio.ui.fragment.AnasayfaFragmentDirections
import com.merve.nomio.ui.viewmodel.AnasayfaViewModel
import com.merve.nomio.utils.gecisYap

class YemeklerAdapter(var mContext: Context,
                      var yemeklerListesi:List<Yemekler>,
                      var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.YemekCardTasarimTutucu>(){

    inner class YemekCardTasarimTutucu(var tasarimYemek:YemekCardTasarimBinding):
        RecyclerView.ViewHolder(tasarimYemek.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YemekCardTasarimTutucu {
        val tasarimYemek= YemekCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return YemekCardTasarimTutucu(tasarimYemek)
    }

    override fun onBindViewHolder(holder: YemeklerAdapter.YemekCardTasarimTutucu, position: Int) {
        val yemek=yemeklerListesi.get(position)
        val tYemek=holder.tasarimYemek

        tYemek.detayYemekAdi.text= yemek.yemek_adi
        tYemek.detayFiyat.text= "${yemek.yemek_fiyat.toString()}₺"

        val imageUrl="http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

        Glide.with(mContext)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error_resmi)
            .into(tYemek.iVYemek)

        tYemek.btnSepet.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.anasayfaDetayGecis(yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

}