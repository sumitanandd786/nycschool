package com.example.a20220216_sumitanand_nycschools.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20220216_sumitanand_nycschools.R
import com.example.a20220216_sumitanand_nycschools.databinding.ActivityMainBinding
import com.example.a20220216_sumitanand_nycschools.model.HighSchoolResultsItem
import com.example.a20220216_sumitanand_nycschools.model.ResponseHighSchool
import com.example.a20220216_sumitanand_nycschools.utils.CheckValidation
import com.example.a20220216_sumitanand_nycschools.utils.EventLogs
import com.example.a20220216_sumitanand_nycschools.utils.PaginationScrollListener
import com.example.a20220216_sumitanand_nycschools.view.adapters.HighSchoolListAdapter
import com.example.a20220216_sumitanand_nycschools.viewmodel.HighSchoolViewModel
import java.util.concurrent.TimeoutException

class MainActivity : AppCompatActivity() {
    private val pageStart: Int = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var totalPages: Int = 1
    private var currentPage: Int = pageStart
    private lateinit var binding : ActivityMainBinding
    private lateinit var homeViewModel: HighSchoolViewModel
    private lateinit var mAdapter: HighSchoolListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        homeViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(HighSchoolViewModel::class.java)

        initMyOrderRecyclerView()
        observerDataRequest()
    }

    private fun initMyOrderRecyclerView() {
        //attach adapter to  recycler
        mAdapter = HighSchoolListAdapter(this@MainActivity)
        binding.adapterHighSchool = mAdapter
        binding.recyclerMyOrders.setHasFixedSize(true)
        binding.recyclerMyOrders.itemAnimator = DefaultItemAnimator()

        loadFirstPage()

        binding.recyclerMyOrders.addOnScrollListener(object : PaginationScrollListener(binding.recyclerMyOrders.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1

                Handler(Looper.myLooper()!!).postDelayed({
                    loadNextPage()
                }, 1000)
            }

            override fun getTotalPageCount(): Int {
                return totalPages
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }

    private fun loadFirstPage() {
        hideErrorView()
        if (CheckValidation.isConnected(this)) {
            homeViewModel.requestFirstPage(currentPage)
        }else{
            showErrorView(null)
        }
    }

    fun loadNextPage() {
        if (CheckValidation.isConnected(this)) {
            homeViewModel.requestNextPage(currentPage)
        }else{
            mAdapter.showRetry(true, fetchErrorMessage(null))
        }
    }

    private fun observerDataRequest(){
        homeViewModel.firstPageResponse.observe(this) {
            if (it is ResponseHighSchool) {
                hideErrorView()
                val results: MutableList<HighSchoolResultsItem> = fetchResults(it) as MutableList<HighSchoolResultsItem>
                binding.mainProgress.visibility = View.GONE
                mAdapter.addAll(results)
               // totalPages = it.totalPages!!

                if (currentPage <= totalPages) mAdapter.addLoadingFooter()
                else isLastPage = true
            }else if (it is Throwable){
                showErrorView(it)
            }else{
                EventLogs.setLogCat("TAG_TEST" , "Error First Page")
            }
        }

        homeViewModel.nextPageResponse.observe(this) {
            if (it is ResponseHighSchool) {

                val results = fetchResults(it) as MutableList<HighSchoolResultsItem>
                mAdapter.removeLoadingFooter()
                isLoading = false
                mAdapter.addAll(results)

                if (currentPage != totalPages) mAdapter.addLoadingFooter()
                else isLastPage = true

            }else if (it is Throwable){
                mAdapter.showRetry(true, fetchErrorMessage(it))
            }else{
                EventLogs.setLogCat("TAG_TEST" , "Error First Page")
            }

        }
    }

    private fun fetchResults(model: ResponseHighSchool): List<HighSchoolResultsItem>? {
        return model.results
    }

    private fun showErrorView(throwable: Throwable?) {
        if (binding.lyError.errorLayout.visibility == View.GONE) {
            binding.lyError.errorLayout.visibility = View.VISIBLE
            binding.mainProgress.visibility = View.GONE

            if (!CheckValidation.isConnected(this)) {
                binding.lyError.errorTxtCause.setText(R.string.error_msg_no_internet)
            } else {
                if (throwable is TimeoutException) {
                    binding.lyError.errorTxtCause.setText(R.string.error_msg_timeout)
                } else {
                    binding.lyError.errorTxtCause.setText(R.string.error_msg_unknown)
                }
            }
        }
    }

    private fun hideErrorView() {
        if (binding.lyError.errorLayout.visibility == View.VISIBLE) {
            binding.lyError.errorLayout.visibility = View.GONE
            binding.mainProgress.visibility = View.VISIBLE
        }
    }

    private fun fetchErrorMessage(throwable: Throwable?): String {
        var errorMsg: String = resources.getString(R.string.error_msg_unknown)

        if (!CheckValidation.isConnected(this)) {
            errorMsg = resources.getString(R.string.error_msg_no_internet)
        } else if (throwable is TimeoutException) {
            errorMsg = resources.getString(R.string.error_msg_timeout)
        }

        return errorMsg
    }
}