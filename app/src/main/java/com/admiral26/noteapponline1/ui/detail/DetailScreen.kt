package com.admiral26.noteapponline1.ui.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.admiral26.noteapponline1.R
import com.admiral26.noteapponline1.core.model.request.NoteRequest
import com.admiral26.noteapponline1.databinding.ScreenDetailBinding
import com.admiral26.noteapponline1.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailScreen : BaseFragment(R.layout.screen_detail), View.OnClickListener {
    private val args: DetailScreenArgs by navArgs()
    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModelImp>()

    private var title: String? = null
    private var desc: String? = null
    private var priority: Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    val dataTime = LocalDateTime.now().toString()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreated(view: View, savedInstanceState: Bundle?) {
        if (args.bool) {
            binding.detailProgres.visibility = View.VISIBLE
            viewModel.getItemNote(args.id)
            observeDetail()
        } else {
            binding.one.visibility = View.VISIBLE
            binding.two.visibility = View.VISIBLE
            binding.three.visibility = View.VISIBLE
        }
        actions()
        listenAction()

    }

    private fun actions() {
        binding.one.setOnClickListener(this)
        binding.two.setOnClickListener(this)
        binding.three.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeDetail() {
        viewModel.noteLd.observe(viewLifecycleOwner) {
            val dateTime = LocalDateTime.parse(it?.data, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            binding.detailProgres.visibility = View.GONE
            binding.etNoteTitle.setText(it?.title)
            binding.etNoteDesc.setText(it?.body)
            binding.tvDateTime.text = "${dateTime.dayOfMonth}/${dateTime.monthValue}/${dateTime.year}"
            when (it?.priority) {
                1 -> {
                    binding.one.visibility = View.VISIBLE
                    binding.two.visibility = View.GONE
                    binding.three.visibility = View.GONE
                }

                2 -> {
                    binding.one.visibility = View.GONE
                    binding.two.visibility = View.VISIBLE
                    binding.three.visibility = View.GONE
                }

                3 -> {
                    binding.one.visibility = View.GONE
                    binding.two.visibility = View.GONE
                    binding.three.visibility = View.VISIBLE
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun listenAction() {
        binding.done.setOnClickListener {
            binding.detailProgres.visibility = View.VISIBLE
            lifecycleScope.launch {
                delay(3000)
                findNavController().popBackStack()
                if (!args.bool){
                    createNote()
                }
            }
        }

        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNote() {
        title = binding.etNoteTitle.text.toString()
        desc = binding.etNoteDesc.text.toString()

        if (title!!.isBlank() || desc!!.isBlank()) {
            Snackbar.make(binding.root, "Ohirigacha to'ldiring", Snackbar.LENGTH_SHORT).show()
        }
        val data = NoteRequest(
            body = desc!!,
            data = dataTime,
            priority = priority!!,
            title = title!!,
        )
        Log.d("TAG66", "createNewNote: ${data.toString()}")

        viewModel.createNote(data)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.one -> {
                if (binding.two.visibility == View.GONE && binding.three.visibility == View.GONE) {
                    binding.one.visibility = View.VISIBLE
                    binding.two.visibility = View.VISIBLE
                    binding.three.visibility = View.VISIBLE
                } else {
                    priority = 1
                    binding.one.visibility = View.VISIBLE
                    binding.two.visibility = View.GONE
                    binding.three.visibility = View.GONE
                }

            }

            R.id.two -> {
                if (binding.one.visibility == View.GONE && binding.three.visibility == View.GONE) {
                    binding.one.visibility = View.VISIBLE
                    binding.two.visibility = View.VISIBLE
                    binding.three.visibility = View.VISIBLE
                } else {
                    priority = 2
                    binding.one.visibility = View.GONE
                    binding.two.visibility = View.VISIBLE
                    binding.three.visibility = View.GONE
                }
            }

            R.id.three -> {
                if (binding.one.visibility == View.GONE && binding.two.visibility == View.GONE) {
                    binding.one.visibility = View.VISIBLE
                    binding.two.visibility = View.VISIBLE
                    binding.three.visibility = View.VISIBLE
                } else {
                    priority = 3
                    binding.one.visibility = View.GONE
                    binding.two.visibility = View.GONE
                    binding.three.visibility = View.VISIBLE
                }
            }
        }
    }
}