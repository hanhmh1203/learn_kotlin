package keddit.com.egn.keddit.ui.adapter.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
class testParc(val t: String, val x: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(t)
        writeInt(x)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<testParc> = object : Parcelable.Creator<testParc> {
            override fun createFromParcel(source: Parcel): testParc = testParc(source)
            override fun newArray(size: Int): Array<testParc?> = arrayOfNulls(size)
        }
    }
}