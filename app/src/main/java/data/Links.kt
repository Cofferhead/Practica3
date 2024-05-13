package data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Links(
    val previousepisode: Previousepisode?,
    val self: Self?
) : Serializable