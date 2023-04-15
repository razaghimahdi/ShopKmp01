package com.example.shopkmm01.business.core

sealed class UIComponent {


    data class Toast(
        val title:String,
        val description:String
    ): UIComponent()

    data class Dialog(
        val title:String,
        val description:String
    ): UIComponent()

    data class None(
        val message:String,
    ): UIComponent()



}