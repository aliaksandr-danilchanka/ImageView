package danilchanka.aliaksandr.imageview.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import danilchanka.aliaksandr.imageview.R
import danilchanka.aliaksandr.imageview.databinding.FragmentImageViewBinding
import danilchanka.aliaksandr.imageview.util.Utils
import danilchanka.aliaksandr.imageview.view.ImageViewView
import danilchanka.aliaksandr.imageview.viewmodel.ImageViewViewModel
import kotlinx.android.synthetic.main.fragment_image_view.*

class ImageViewFragment : Fragment(), ImageViewView {

    private lateinit var mBinding: FragmentImageViewBinding
    private lateinit var mViewModel: ImageViewViewModel

    companion object {
        fun newInstance(): ImageViewFragment = ImageViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_view, container, false)
        mViewModel = ViewModelProviders.of(this).get(ImageViewViewModel::class.java)
        mBinding.imageViewModel = mViewModel
        mViewModel.attachView(this)
        return mBinding.root
    }

    override fun onDetach() {
        super.onDetach()
        mViewModel.detachView()
    }

    override fun onErrorConnection() {
        Toast.makeText(context, R.string.internet_connection_error, Toast.LENGTH_SHORT).show()
    }

    override fun onError() {
        Toast.makeText(context, R.string.wrong_data, Toast.LENGTH_SHORT).show()
    }

    override fun hideSoftKeyboard() {
        Utils.hideSoftKeyboard(context!!, imageView)
    }
}