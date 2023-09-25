package task.abdur.moviedb.models

import android.os.Parcel
import android.os.Parcelable
data class TVResponse(
    val page: Int,
    val results: List<TV>,
    val total_pages: Int,
    val total_results: Int
)

data class TV(
    val id: Int,
    val name: String,
    val backdrop_path: String,
    val poster_path: String,
    val overview: String,
    val first_air_date: String,
    val vote_average: Double,
    val isFavorite: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(backdrop_path)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeString(first_air_date)
        parcel.writeDouble(vote_average)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TV> {
        override fun createFromParcel(parcel: Parcel): TV {
            return TV(parcel)
        }

        override fun newArray(size: Int): Array<TV?> {
            return arrayOfNulls(size)
        }
    }
}

