package ru.razuvaev.kotlin_one.model

interface FragmentSendDataListener {
    fun onSendData(data: Film, action: String)
}