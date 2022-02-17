package com.example.a20220216_sumitanand_nycschools.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a20220216_sumitanand_nycschools.repository.RetrofitRepository

class HighSchoolViewModel(application: Application) : AndroidViewModel(application) {
    private var retrofitRepository: RetrofitRepository? = RetrofitRepository.getInstance(application)
    private var _firstPageResponse = MutableLiveData<Any>()
    private var _nextPageResponse = MutableLiveData<Any>()

    fun requestFirstPage(page : Int) {
        retrofitRepository!!.loadPage(_firstPageResponse , page)
    }

    fun requestNextPage(page : Int) {
        retrofitRepository!!.loadPage(_nextPageResponse , page)
    }

    val firstPageResponse : LiveData<Any>
        get() = _firstPageResponse
    val nextPageResponse : LiveData<Any>
        get() = _nextPageResponse

}