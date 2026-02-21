package uz.gita.maxwayclone.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData
import uz.gita.maxwayclone.presentation.pages.StoriesViewPager

class StoriesViewPagerAdapter(
    fm : FragmentManager , lifecycle: Lifecycle) : FragmentStateAdapter(fm , lifecycle) {


    private val storiesList  = mutableListOf<StoriesResponseData>()

    override fun createFragment(position: Int): Fragment {
        return StoriesViewPager.getInstance(storiesList[position])
    }

    override fun getItemCount(): Int=storiesList.size


    fun submitList(stories: List<StoriesResponseData>){
        storiesList.clear()
        storiesList.addAll(stories)
        notifyDataSetChanged()
    }


}