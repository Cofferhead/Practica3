package data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Rating(
    val average: Double
) : Serializable