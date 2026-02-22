package uz.gita.maxwayclone.presentation.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import uz.gita.maxwayclone.R
import uz.gita.maxwayclone.databinding.FragmentHomeBinding
import uz.gita.maxwayclone.presentation.adapter.AdsAdapter
import kotlin.getValue

class HomeFragment: Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels { HomeViewModelFactory() }
    private lateinit var adapter: AdsAdapter

    private val handler = Handler(Looper.getMainLooper())

    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val viewPager = _binding?.viewPagerCarousel ?: return
            if (adapter.itemCount == 0) return

            val nextItem = viewPager.currentItem + 1
            viewPager.setCurrentItem(nextItem, true)

            handler.removeCallbacks(this)
            handler.postDelayed(this, 4000)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        setupAdapter()
        observeViewModel()

        viewModel.fetchAds()


    }
    private fun setupAdapter() {
        adapter = AdsAdapter()
        binding.viewPagerCarousel.adapter = adapter

        binding.viewPagerCarousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    handler.removeCallbacks(autoScrollRunnable)
                }
                else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    resetAutoScroll()
                }
            }
        })
    }
    private fun observeViewModel() {
        viewModel.adsLiveData.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) return@observe

            adapter.submitList(list) {
                val middle = 10000 / 2
                val startPosition = middle - (middle % list.size)

                binding.viewPagerCarousel.setCurrentItem(startPosition, false)

                resetAutoScroll()
            }
        }
    }
    private fun resetAutoScroll() {
        handler.removeCallbacks(autoScrollRunnable)
        handler.postDelayed(autoScrollRunnable, 4000)
    }
    override fun onPause() {
        handler.removeCallbacks(autoScrollRunnable)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        resetAutoScroll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(autoScrollRunnable)
        _binding = null
    }
}