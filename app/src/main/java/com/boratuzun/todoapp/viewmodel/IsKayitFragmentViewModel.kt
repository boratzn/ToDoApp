package com.boratuzun.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.boratuzun.todoapp.data.repo.IslerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IsKayitFragmentViewModel @Inject constructor(var irepo:IslerDaoRepository): ViewModel() {
    fun kayit(yapilacak_is:String){
        irepo.isKayit(yapilacak_is)
    }
}