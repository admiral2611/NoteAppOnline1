package com.admiral26.noteapponline1.core.model.request


import com.google.gson.annotations.SerializedName

data class NoteRequest(
    @SerializedName("body")
    val body: String, // body 1
    @SerializedName("data")
    val data: String, // data 1
    @SerializedName("priority")
    val priority: Int, // 28
    @SerializedName("title")
    val title: String // title 1
)