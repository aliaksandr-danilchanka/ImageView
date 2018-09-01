package danilchanka.aliaksandr.imageview.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.math.BigInteger
import java.security.MessageDigest


class Utils {

    companion object {

        const val BASE_URL = "https://mobility.cleverlance.com"
        const val BASE_LOGIN = "danilchanka"
        const val BASE_PASSWORD = "aliaksandr"

        fun stringToSha1(input: String): String {
            var sha1 = ""
            try {
                val digest = MessageDigest.getInstance("SHA-1")
                digest.reset()
                digest.update(input.toByteArray(Charsets.UTF_8))
                sha1 = String.format("%040x", BigInteger(1, digest.digest()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return sha1
        }

        fun stringBase64ToBitmap(encodedImage: String): Bitmap {
            val decodedString = Base64.decode(encodedImage, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }

        fun hideSoftKeyboard(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}