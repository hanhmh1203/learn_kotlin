package keddit.com.egn.keddit.ui.adapter.model

import android.os.Parcel
import android.os.Parcelable
import keddit.com.egn.keddit.commons.AdapterConstrants
import keddit.com.egn.keddit.ui.adapter.commons.ViewType

data class RedditNews(val before: String, val after: String, val news: List<RedditNewsItem>) : Parcelable {
    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNews> = object : Parcelable.Creator<RedditNews> {
            override fun createFromParcel(source: Parcel): RedditNews = RedditNews(source)
            override fun newArray(size: Int): Array<RedditNews?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.createTypedArrayList(RedditNewsItem.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(after)
        dest?.writeString(before)
        dest?.writeTypedList(news)
    }
}

data class RedditNewsItem(val author: String,
                          val title: String,
                          val numComments: Int,
                          val created: Long,
                          val thumbnail: String,
                          val url: String?) : ViewType, Parcelable {

    override fun getViewType() = AdapterConstrants.NEWS

    override fun writeToParcel(dest: Parcel?, p1: Int) {
        dest?.writeString(author)
        dest?.writeString(title)
        dest?.writeInt(numComments)
        dest?.writeLong(created)
        dest?.writeString(thumbnail)
        dest?.writeString(url)

    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RedditNewsItem> = object : Parcelable.Creator<RedditNewsItem> {
            override fun createFromParcel(source: Parcel): RedditNewsItem = RedditNewsItem(source)
            override fun newArray(size: Int): Array<RedditNewsItem?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readInt(), source.readLong(), source.readString(), source.readString())

    override fun describeContents() = 0

//    class Builder() {
//        private var author: String = ""
//        private var title: String = ""
//        private var numComments: Int = 0
//        private var created: Long = 0
//        private var thumbnail: String = ""
//        private var url: String = ""
//
//        fun addAuthor(au: String) = apply { this.author = au }
//        fun addTitle(title: String) = apply { this.title = title }
//        fun addNumComments(num: Int) = apply { this.numComments = num }
//        fun addCreated(created: Long) = apply { this.created = created }
//        fun addThumbnail(thumb: String) = apply { this.thumbnail = thumbnail }
//        fun addUrl(url: String) = apply { this.url = url }
//        fun builder() = RedditNewsItem(author, title)
//    }
}