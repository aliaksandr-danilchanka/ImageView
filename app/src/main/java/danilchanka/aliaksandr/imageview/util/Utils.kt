package danilchanka.aliaksandr.imageview.util

import android.app.Activity
import android.view.View
import android.view.WindowManager

class Utils {

    companion object {
        fun requestFocus(view: View, activity: Activity?) {
            if (view.requestFocus() && activity != null) {
                activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }
    }

}