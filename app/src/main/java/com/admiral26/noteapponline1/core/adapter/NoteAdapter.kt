package com.admiral26.noteapponline1.core.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.admiral26.noteapponline1.R
import com.admiral26.noteapponline1.core.model.respons.NoteResponse
import com.admiral26.noteapponline1.core.model.respons.NoteResponseItem
import com.admiral26.noteapponline1.databinding.ItemRvNotesBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val data = ArrayList<NoteResponseItem>()
    var onClick: ((id: String) -> Unit)? = null
    var delete: ((id: String) -> Unit)? = null

    fun deleteData(data: NoteResponseItem) {
        this.data.clear()
        this.data.addAll(listOf(data))
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: NoteResponse) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ItemRvNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bindData(data: NoteResponseItem) {

            val dateTime = LocalDateTime.parse(data.data, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
           // val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("hh:mm"))


            binding.tvTitle.text = data.title
            binding.tvDesc.text = data.body
            binding.tvDateTime.text = "${dateTime.dayOfMonth}/${dateTime.monthValue}/${dateTime.year}"

            when (data.priority) {
                1 -> {
                    binding.cardView.setCardBackgroundColor(
                        binding.cardView.context.resources.getColor(
                            R.color.one
                        )
                    )
                }

                2 -> {
                    binding.cardView.setCardBackgroundColor(
                        binding.cardView.context.resources.getColor(
                            R.color.two
                        )
                    )
                }

                3 -> {
                    binding.cardView.setCardBackgroundColor(
                        binding.cardView.context.resources.getColor(
                            R.color.thee
                        )
                    )
                }
            }
            itemView.setOnClickListener {
                onClick?.invoke(data.id)
            }
            itemView.setOnLongClickListener {
                delete?.invoke(data.id)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        return NoteViewHolder(
            ItemRvNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.bindData((data[position]))
    }


    override fun getItemCount() = data.size

}