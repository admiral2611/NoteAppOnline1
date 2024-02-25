package com.admiral26.noteapponline1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.admiral26.noteapponline1.R
import com.admiral26.noteapponline1.core.adapter.NoteAdapter
import com.admiral26.noteapponline1.databinding.PegeHomeBinding
import com.admiral26.noteapponline1.ui.base.BaseFragment

class HomePage : BaseFragment(R.layout.pege_home) {
    private val binding by viewBinding(PegeHomeBinding::bind)
    private val adapter by lazy { NoteAdapter() }
    private val viewModel by viewModels<HomeViewModelImp>()
    override fun onCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        viewModel.getNotes()
        observe()
        listenAction()
    }

    private fun listenAction() {
        adapter.onClick ={
            findNavController().navigate(HomePageDirections.actionHomePageToDetailScreen(it.toInt(),true))
        }

        adapter.delete ={
            viewModel.deleteNote(it.toInt())
        }
        binding.createNoteBtn.setOnClickListener{
            findNavController().navigate(HomePageDirections.actionHomePageToDetailScreen(0,false))
        }
    }

    private fun observe() {
        viewModel.noteLd.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setData(it)
                binding.progres.visibility = View.GONE
            }
            if (it?.size == 0) {
                binding.emptyText.visibility = View.VISIBLE
            }

        }

        viewModel.deleteLd.observe(viewLifecycleOwner){
            it?.let { it1 -> adapter.deleteData(it1) }
            viewModel.getNotes()
        }
    }

    private fun setAdapter() {
        binding.rvList.adapter = adapter
        binding.rvList.setHasFixedSize(true)
        binding.rvList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
}