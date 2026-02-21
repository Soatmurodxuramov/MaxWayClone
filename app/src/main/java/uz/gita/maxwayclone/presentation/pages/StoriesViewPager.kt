package uz.gita.maxwayclone.presentation.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData
import uz.gita.maxwayclone.databinding.StoriesviewerBinding
import uz.gita.maxwayclone.databinding.StoriesviewpagerBinding

class StoriesViewPager : Fragment() {
    private lateinit var binding : StoriesviewpagerBinding
    companion object{
        fun getInstance(storiesResponseData: StoriesResponseData) : StoriesViewPager{
            val fragment = StoriesViewPager()
            val bundle = Bundle()
            val result = bundle.putParcelable("data" , storiesResponseData)
            Log.d("TTT", "getInstance: Pages")
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StoriesviewpagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var data : StoriesResponseData?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data= arguments?.getParcelable("data")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = StoriesviewpagerBinding.bind(view)
        Glide.with(binding.imgads).load(data?.url).into(binding.imgads)
        binding.tittleName.text = data?.name
    }




}