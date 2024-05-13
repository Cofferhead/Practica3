package data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Externals(
    val imdb: String?,
    val thetvdb: Int,
    val tvrage: Int
):Serializable