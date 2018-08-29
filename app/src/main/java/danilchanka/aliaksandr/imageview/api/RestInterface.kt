package danilchanka.aliaksandr.imageview.api

import danilchanka.aliaksandr.imageview.model.ImageInformation
import io.reactivex.Observable
import retrofit2.http.*

interface RestInterface {

    @FormUrlEncoded
    @POST("/download/bootcamp/image.php")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getImageBase64Encoded(@Field("username") username: String,
                              @Header("Authorization") password: String): Observable<ImageInformation>
}