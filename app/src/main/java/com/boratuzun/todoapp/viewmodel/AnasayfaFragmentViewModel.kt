package com.boratuzun.todoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boratuzun.todoapp.data.entity.Isler
import com.boratuzun.todoapp.data.repo.IslerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaFragmentViewModel @Inject constructor(var irepo:IslerDaoRepository): ViewModel() {
    var islerListesi = MutableLiveData<List<Isler>>()

    init {
        isleriYukle()
        islerListesi = irepo.isleriGetir()
    }

    fun ara(aramaKelimesi:String) {
        irepo.isAra(aramaKelimesi)
    }

    fun sil(yapilacak_id:Int) {
        irepo.isSil(yapilacak_id)
    }

    fun isleriYukle() {
        irepo.tumIsleriAl()
    }
}