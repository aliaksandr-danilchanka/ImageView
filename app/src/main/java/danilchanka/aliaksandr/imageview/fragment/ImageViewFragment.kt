package danilchanka.aliaksandr.imageview.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import danilchanka.aliaksandr.imageview.R
import danilchanka.aliaksandr.imageview.databinding.FragmentImageViewBinding
import danilchanka.aliaksandr.imageview.viewmodel.ImageViewViewModel

class ImageViewFragment : Fragment() {

    private lateinit var mBinding: FragmentImageViewBinding
    private lateinit var mModel: ImageViewViewModel

    companion object {
        fun newInstance(): ImageViewFragment = ImageViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_view, container, false)
        mModel = ViewModelProviders.of(this).get(ImageViewViewModel::class.java)
        mBinding.imageViewModel = mModel
        return mBinding.root
    }
}