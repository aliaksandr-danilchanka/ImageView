package danilchanka.aliaksandr.imageview.activity

import android.os.Bundle
import danilchanka.aliaksandr.imageview.R
import danilchanka.aliaksandr.imageview.activity.base.BaseFragmentActivity
import danilchanka.aliaksandr.imageview.fragment.ImageViewFragment
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : BaseFragmentActivity() {

    override fun getFragmentContainerId(): Int = R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
        setUpAppBar()

        if (savedInstanceState == null) {
            addFragment(ImageViewFragment.newInstance())
        }
    }

    private fun setUpAppBar() {
        setSupportActionBar(toolbar)
    }
}
