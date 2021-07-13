package kr.hs.dgsw.mmr.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.mmr.R
import kr.hs.dgsw.mmr.base.BaseFragment
import kr.hs.dgsw.mmr.databinding.FragmentHomeBinding
import kr.hs.dgsw.mmr.factory.NoParameterViewModelFactory
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.view.adapter.HomePostListAdapter
import kr.hs.dgsw.mmr.viewmodel.fragment.HomeViewModel
import kr.hs.dgsw.mmr.viewmodel.fragment.WriteViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(
            viewModelStore,
            NoParameterViewModelFactory()
        ).get(HomeViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)


    }

    override fun observerViewModel() {
        mBinding.rvPostList.adapter = viewModel.adapter
        mBinding.homeRefresh.setOnRefreshListener {
            mBinding.homeRefresh.isRefreshing = false
            mViewModel.getAllPost()
        }

        with(mViewModel) {
            adapter.context = this@HomeFragment.requireContext()
            postGetAll.observe(this@HomeFragment) {
                adapter.postResponse = it as ArrayList<PostResponse>
                adapter.notifyDataSetChanged()
            }
        }
    }
}