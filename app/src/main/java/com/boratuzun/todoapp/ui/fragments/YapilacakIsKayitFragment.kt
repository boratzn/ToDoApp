package com.boratuzun.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.boratuzun.todoapp.R
import com.boratuzun.todoapp.databinding.FragmentYapilacakIsDetayBinding
import com.boratuzun.todoapp.databinding.FragmentYapilacakIsKayitBinding
import com.boratuzun.todoapp.viewmodel.IsDetayFragmentViewModel
import com.boratuzun.todoapp.viewmodel.IsKayitFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YapilacakIsKayitFragment : Fragment() {
    private lateinit var tasarim:FragmentYapilacakIsKayitBinding
    private lateinit var viewModel:IsKayitFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yapilacak_is_kayit,container, false)

        tasarim.isKayitFragment = this
        tasarim.isKayitToolbarBaslik = "İş Kayıt"

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:IsKayitFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun buttonKaydetTikla(yapilacak_is:String){
        viewModel.kayit(yapilacak_is)
    }
}