package com.boratuzun.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.boratuzun.todoapp.data.repo.IslerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IsDetayFragmentViewModel @Inject constructor(var irepo:IslerDaoRepository): ViewModel() {

    fun guncelle(yapilacak_id:Int, yapilacak_is:String) {
        irepo.isGuncelle(yapilacak_id, yapilacak_is)
    }
}