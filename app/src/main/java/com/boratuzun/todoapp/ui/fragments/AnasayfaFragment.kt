@file:Suppress("DEPRECATION")

package com.boratuzun.todoapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.boratuzun.todoapp.R
import com.boratuzun.todoapp.databinding.FragmentAnasayfaBinding
import com.boratuzun.todoapp.ui.adapter.IslerAdapter
import com.boratuzun.todoapp.util.gecisYap
import com.boratuzun.todoapp.viewmodel.AnasayfaFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var tasarim: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa,container, false)
        tasarim.anasayfaFragment = this
        tasarim.anasayfaToolbarBaslik = "Yapılacak İşler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        tasarim.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.islerListesi.observe(viewLifecycleOwner) {
            val adapter = IslerAdapter(requireContext(), it, viewModel)
            tasarim.islerAdapter = adapter
        }

        tasarim.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.kayitGecis)
        }

        return tasarim.root
    }

    fun fabTikla(view:View){
        Navigation.gecisYap(view, R.id.kayitGecis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//Fragmentlar üzerinde kullanılması gereken kod. Activity sayfalarında gerek yok.
        val tempViewModel:AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)

        val item = menu.findItem(R.id.action_ara)
        val searcView = item.actionView as SearchView
        searcView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {//Klavye üzerindeki arama ikonu ile çalışır.
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {//Klavyeden harf girdikçe ve sildikçe çalışır.
        viewModel.ara(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.isleriYukle()
    }
}