package danilchanka.aliaksandr.imageview.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import danilchanka.aliaksandr.imageview.R
import danilchanka.aliaksandr.imageview.databinding.FragmentImageViewBinding
import danilchanka.aliaksandr.imageview.util.OnTextChangedTextWatcher
import danilchanka.aliaksandr.imageview.util.Utils
import danilchanka.aliaksandr.imageview.view.ImageViewView
import danilchanka.aliaksandr.imageview.viewmodel.ImageViewViewModel
import kotlinx.android.synthetic.main.fragment_image_view.*

class ImageViewFragment : Fragment(), ImageViewView {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTextChangedListener()
    }

    override fun showNormal() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateFields(): Boolean {
        return validateNonEmptyField(editLogin, inputLayoutLogin) and validateNonEmptyField(editPassword, inputLayoutPassword)
    }

    override fun addValidatingTextWatchers() {
        editLogin.addTextChangedListener(object : OnTextChangedTextWatcher() {
            override fun onTextChanged(s: String) {
                validateNonEmptyField(editLogin, inputLayoutLogin)
            }
        })
        editPassword.addTextChangedListener(object : OnTextChangedTextWatcher() {
            override fun onTextChanged(s: String) {
                validateNonEmptyField(editPassword, inputLayoutPassword)
            }
        })
    }

    private fun validateNonEmptyField(editText: EditText, inputLayout: TextInputLayout): Boolean {
        val value = editText.text.toString().trim { it <= ' ' }

        if (value.isEmpty()) {
            inputLayout.isErrorEnabled = true
            inputLayout.error = getString(R.string.errorEmptyField)
            Utils.requestFocus(editText, activity)
            return false
        } else {
            inputLayout.isErrorEnabled = false
        }

        return true
    }

    private fun addTextChangedListener() {
        editLogin.addTextChangedListener((object : OnTextChangedTextWatcher() {
            override fun onTextChanged(s: String) {
                mModel.login.set(s)
            }
        }))
        editPassword.addTextChangedListener((object : OnTextChangedTextWatcher() {
            override fun onTextChanged(s: String) {
                mModel.password.set(s)
            }
        }))
    }
}