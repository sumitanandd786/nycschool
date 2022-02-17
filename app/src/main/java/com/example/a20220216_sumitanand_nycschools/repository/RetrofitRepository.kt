package com.example.a20220216_sumitanand_nycschools.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.a20220216_sumitanand_nycschools.api.API
import com.example.a20220216_sumitanand_nycschools.api.ApiClient
import com.example.a20220216_sumitanand_nycschools.model.ResponseHighSchool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRepository private constructor(app: Application) {
    private var instanceApi: API

    init {
        ApiClient.init(app)
        instanceApi = ApiClient.instance
    }

    companion object {
        private var retrofitRepository: RetrofitRepository? = null

        @Synchronized
        fun getInstance(app: Application): RetrofitRepository? {
            if (retrofitRepository == null) {
                retrofitRepository = RetrofitRepository(app)
            }
            return retrofitRepository
        }
    }


    fun loadPage(toResponse: MutableLiveData<Any>, page: Int) {
        instanceApi.getHighSchoolList(pageIndex = page)
            .enqueue(object : Callback<ResponseHighSchool> {
                override fun onResponse(
                    call: Call<ResponseHighSchool>,
                    response: Response<ResponseHighSchool>
                ) {
                    if (response.isSuccessful) {
                        toResponse.value = response.body()
                    } else {
                        toResponse.value = "error"
                    }
                }

                override fun onFailure(call: Call<ResponseHighSchool>, t: Throwable) {
                    toResponse.value = t
                }
            })
    }

}