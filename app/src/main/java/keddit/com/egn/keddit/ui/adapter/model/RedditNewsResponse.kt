package keddit.com.egn.keddit.ui.adapter.model

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
class RedditNewsResponse(val data: RedditDataResponse) {
    class RedditDataResponse(
            val children: List<RedditChildrenResponse>,
            val after: String?,
            val before: String?
    )

    class RedditChildrenResponse(val data: RedditNewsDataResponse)

    class RedditNewsDataResponse(
            val author: String,
            val title: String,
            val num_comments: Int,
            val created: Long,
            val thumbnail: String,
            val url: String
    )
}