package com.zg.burgerjoint.mvp.views

interface LoginView : BaseView{
    fun navigateToMainScreen()
    fun showErrorMessage(message : String)
}