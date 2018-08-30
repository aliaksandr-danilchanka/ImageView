package danilchanka.aliaksandr.imageview.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.math.BigInteger
import java.security.MessageDigest


class Utils {

    companion object {

        const val BASE_URL = "https://mobility.cleverlance.com"
        const val HTTP_ERROR_MESSAGE = "HTTP 401 Unauthorized"

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
    }

}