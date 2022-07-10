package com.boratuzun.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.boratuzun.todoapp.R
import com.boratuzun.todoapp.data.entity.Isler
import com.boratuzun.todoapp.databinding.CardTasarimBinding
import com.boratuzun.todoapp.ui.fragments.AnasayfaFragment
import com.boratuzun.todoapp.ui.fragments.AnasayfaFragmentDirections
import com.boratuzun.todoapp.util.gecisYap
import com.boratuzun.todoapp.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class IslerAdapter(var mContext:Context,
                   var islerListesi:List<Isler>,
                   var viewModel:AnasayfaFragmentViewModel)
        : RecyclerView.Adapter<IslerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:CardTasarimBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarim,parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val is1 = islerListesi.get(position)
        val t = holder.tasarim
        t.isNesnesi = is1

        t.textViewIsBilgi.text = "${is1.yapilacak_is}"

        t.imageViewSilResim.setOnClickListener {
            Snackbar.make(it, "${is1.yapilacak_is} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("EVET"){
                    viewModel.sil(is1.yapilacak_id)
                }.show()
        }

        t.satirCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(is1)
            Navigation.gecisYap(it, gecis)
        }
    }

    override fun getItemCount(): Int {
        return  islerListesi.size
    }
}