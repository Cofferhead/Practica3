package data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

data class Schedule(
    val days: List<String>?,
    val time: String?
):Serializable