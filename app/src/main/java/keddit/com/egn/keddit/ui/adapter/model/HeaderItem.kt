package keddit.com.egn.keddit.ui.adapter.model

import android.os.Parcel
import android.os.Parcelable
import keddit.com.egn.keddit.commons.AdapterConstrants
import keddit.com.egn.keddit.ui.adapter.commons.ViewType

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
data class HeaderItem(val left: String = "Left", val right: String = "Right", val center: String = "Center") : ViewType, Parcelable {
    override fun getViewType() = AdapterConstrants.HEADER

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(left)
        writeString(right)
        writeString(center)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<HeaderItem> = object : Parcelable.Creator<HeaderItem> {
            override fun createFromParcel(source: Parcel): HeaderItem = HeaderItem(source)
            override fun newArray(size: Int): Array<HeaderItem?> = arrayOfNulls(size)
        }
    }
}