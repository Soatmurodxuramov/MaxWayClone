package uz.gita.maxwayclone.presentation.screens.storiesViewer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import uz.gita.maxwayclone.R
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData
import uz.gita.maxwayclone.databinding.StoriesviewerBinding
import uz.gita.maxwayclone.presentation.adapters.StoriesViewPagerAdapter

class StoriesViewer : Fragment(R.layout.storiesviewer) {
    private val binding by viewBinding(StoriesviewerBinding::bind)
    private val viewModel : StoriesViewerViewModel by viewModels<StoriesViewerViewModelImpl>{ StoriesViewerVMFactory() }
    private lateinit var adapter : StoriesViewPagerAdapter

    private var job : Job?=null
    override fun onStart() {
        super.onStart()
        viewModel.errorMessageLiveData.observe(this , errorMessageLiveDataObserver)
        viewModel.successLiveData.observe(this , successLiveDataObserver)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = StoriesViewPagerAdapter(childFragmentManager , lifecycle)
        viewModel.getStories()
        binding.viewpager2.adapter = adapter
        startAutoSwipe()
    }

    private fun startAutoSwipe(){
        job = lifecycleScope.launch {
            while (isActive){
                delay(5000)
                val itemCount = adapter.itemCount
                if(itemCount == 0) continue
                val next = (binding.viewpager2.currentItem+1) %adapter.itemCount
                binding.viewpager2.currentItem = next
            }
        }
    }

    private fun stopAutoSwipe(){
        job?.cancel()
    }

    override fun onPause() {
        super.onPause()
        stopAutoSwipe()
    }

//    override fun onResume() {
//        super.onResume()
//        startAutoSwipe()
//    }




    private val errorMessageLiveDataObserver = Observer<String>{
    }

    private val successLiveDataObserver = Observer<List<StoriesResponseData>>{
        adapter.submitList(it)
    }
}