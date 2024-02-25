package com.admiral26.noteapponline1.core.model.respons


import com.google.gson.annotations.SerializedName

data class NoteResponseItem(
    @SerializedName("avatar")
    val avatar: String, // https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/404.jpg
    @SerializedName("body")
    val body: String, // body 1
    @SerializedName("data")
    val data: String, // data 1
    @SerializedName("id")
    val id: String, // 1
    @SerializedName("priority")
    val priority: Int, // 28
    @SerializedName("title")
    val title: String // title 1
)