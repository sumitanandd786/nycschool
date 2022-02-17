package com.example.a20220216_sumitanand_nycschools.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220216_sumitanand_nycschools.BR
import com.example.a20220216_sumitanand_nycschools.R
import com.example.a20220216_sumitanand_nycschools.databinding.ItemHightSchoolBinding
import com.example.a20220216_sumitanand_nycschools.databinding.ItemLoadingBinding
import com.example.a20220216_sumitanand_nycschools.model.HighSchoolResultsItem
import com.example.a20220216_sumitanand_nycschools.view.activities.MainActivity
import com.example.a20220216_sumitanand_nycschools.view.interfaces.PaginationAdapterCallback

class HighSchoolListAdapter(private var mActivity: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    PaginationAdapterCallback {

    private val item: Int = 0
    private val loading: Int = 1

    private var isLoadingAdded: Boolean = false
    private var retryPageLoad: Boolean = false

    private var errorMsg: String? = ""

    private var schoolModels: MutableList<HighSchoolResultsItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  if(viewType == item){
            val binding: ItemHightSchoolBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_hight_school, parent, false)
            HighSchoolVH(binding)
        }else{
            val binding: ItemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_loading, parent, false)
            LoadingVH(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = schoolModels[position]
        if(getItemViewType(position) == item){
            val highSchoolVH: HighSchoolVH = holder as HighSchoolVH
            highSchoolVH.itemRowBinding.tvSchoolEmail.visibility = View.VISIBLE
            highSchoolVH.itemRowBinding.tvSchoolWebsite.visibility = View.VISIBLE
            highSchoolVH.itemRowBinding.tvSchoolFax.visibility = View.VISIBLE
            highSchoolVH.itemRowBinding.tvSchoolPhone.visibility = View.VISIBLE
            highSchoolVH.itemRowBinding.tvSchoolName.visibility = View.VISIBLE
            highSchoolVH.itemRowBinding.tvSchoolLocation.visibility = View.VISIBLE
            highSchoolVH.bind(model)
        }else{
            val loadingVH: LoadingVH = holder as LoadingVH
            if (retryPageLoad) {
                loadingVH.itemRowBinding.loadmoreErrorlayout.visibility = View.VISIBLE
                loadingVH.itemRowBinding.loadmoreProgress.visibility = View.GONE

                if(errorMsg != null) loadingVH.itemRowBinding.loadmoreErrortxt.text = errorMsg
                else loadingVH.itemRowBinding.loadmoreErrortxt.text = mActivity.getString(R.string.error_msg_unknown)

            } else {
                loadingVH.itemRowBinding.loadmoreErrorlayout.visibility = View.GONE
                loadingVH.itemRowBinding.loadmoreProgress.visibility = View.VISIBLE
            }

            loadingVH.itemRowBinding.loadmoreRetry.setOnClickListener{
                showRetry(false, "")
                retryPageLoad()
            }
            loadingVH.itemRowBinding.loadmoreErrorlayout.setOnClickListener{
                showRetry(false, "")
                retryPageLoad()
            }
        }
    }

    override fun getItemCount(): Int {
        return if (schoolModels.size > 0) schoolModels.size else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            item
        }else {
            if (position == schoolModels.size - 1 && isLoadingAdded) {
                loading
            } else {
                item
            }
        }
    }

    override fun retryPageLoad() {
        mActivity.loadNextPage()
    }

    class HighSchoolVH(binding: ItemHightSchoolBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemHightSchoolBinding = binding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model,obj)
            itemRowBinding.executePendingBindings()
        }
    }

    class LoadingVH(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemLoadingBinding = binding
    }

    fun showRetry(show: Boolean, errorMsg: String) {
        retryPageLoad = show
        notifyItemChanged(schoolModels.size - 1)
        this.errorMsg = errorMsg
    }

    fun addAll(highSchoolList: MutableList<HighSchoolResultsItem>) {
        for(movie in highSchoolList){
            add(movie)
        }
    }

    fun add(moive: HighSchoolResultsItem) {
        schoolModels.add(moive)
        notifyItemInserted(schoolModels.size - 1)
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(HighSchoolResultsItem())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position: Int =schoolModels.size -1
        val movie: HighSchoolResultsItem = schoolModels[position]

        if(movie != null){
            schoolModels.removeAt(position)
            notifyItemRemoved(position)
        }
    }


}