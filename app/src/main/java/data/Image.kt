package data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Image(
    val medium: String?,
    val original: String?
):Serializable