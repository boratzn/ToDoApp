package com.boratuzun.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.boratuzun.todoapp.R
import com.boratuzun.todoapp.databinding.FragmentYapilacakIsDetayBinding
import com.boratuzun.todoapp.viewmodel.IsDetayFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YapilacakIsDetayFragment : Fragment() {
    private lateinit var tasarim:FragmentYapilacakIsDetayBinding
    private lateinit var viewModel:IsDetayFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yapilacak_is_detay,container, false)
        tasarim.isDetayFragment = this
        tasarim.isDetayToolbarBaslik = "İş Detay"

        val bundle:YapilacakIsDetayFragmentArgs by navArgs()
        val gelenIs = bundle.yapilacakIs
        tasarim.isNesnesi = gelenIs

        tasarim.yapilacakIsDetayText.setText(gelenIs.yapilacak_is)

        return tasarim.root
    }

    fun buttonGuncelleTikla(yapilacak_id:Int, yapilacak_is:String) {
        viewModel.guncelle(yapilacak_id, yapilacak_is)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:IsDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

}