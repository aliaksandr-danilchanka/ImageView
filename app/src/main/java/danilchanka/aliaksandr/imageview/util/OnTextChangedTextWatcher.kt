package danilchanka.aliaksandr.imageview.util

import android.text.Editable
import android.text.TextWatcher

abstract class OnTextChangedTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onTextChanged(s.toString())
    }

    override fun afterTextChanged(s: Editable) {}

    protected abstract fun onTextChanged(s: String)
}