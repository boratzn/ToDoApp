package com.boratuzun.todoapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.boratuzun.todoapp.data.entity.Isler
import com.boratuzun.todoapp.room.IslerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IslerDaoRepository(var idao:IslerDao) {
    var islerListesi: MutableLiveData<List<Isler>>

    init {
        islerListesi = MutableLiveData()
    }

    fun isKayit(yapilacak_is:String){
        val job = CoroutineScope(Dispatchers.Main).launch {
            idao.isEkle(Isler(0, yapilacak_is))
        }
    }

    fun isGuncelle(yapilacak_id:Int, yapilacak_is: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenenKisi = Isler(yapilacak_id, yapilacak_is)
            idao.isGuncelle(guncellenenKisi)
        }
    }

    fun isAra(aramaKelimesi:String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            islerListesi.value = idao.isArama(aramaKelimesi)
        }
    }

    fun isSil(kisi_id:Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val silinenKisi = Isler(kisi_id, "")
            idao.isSil(silinenKisi)
            tumIsleriAl()
        }
    }

    fun tumIsleriAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            islerListesi.value = idao.tumKisiler()
        }
    }

    fun isleriGetir() : MutableLiveData<List<Isler>> {
        return islerListesi
    }
}