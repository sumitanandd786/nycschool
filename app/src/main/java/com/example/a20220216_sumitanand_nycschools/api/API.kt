package com.example.a20220216_sumitanand_nycschools.api

import com.example.a20220216_sumitanand_nycschools.model.ResponseHighSchool
import com.example.a20220216_sumitanand_nycschools.model.ResponseSatScore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.a20220216_sumitanand_nycschools.utils.HIGH_SCHOOL_API
import com.example.a20220216_sumitanand_nycschools.utils.SAT_SCORE_API

interface API {
    @GET(HIGH_SCHOOL_API)
    fun getHighSchoolList(@Query("page") pageIndex: Int):Call<ResponseHighSchool>

    @GET(SAT_SCORE_API)
    fun getSchoolSatScoreList():Call<ResponseSatScore>

}