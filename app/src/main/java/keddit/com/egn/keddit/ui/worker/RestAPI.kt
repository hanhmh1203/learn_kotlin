package keddit.com.egn.keddit.ui.worker

import keddit.com.egn.keddit.commons.LogI
import keddit.com.egn.keddit.ui.adapter.model.RedditNewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
class RestAPI @Inject constructor() {
    fun isNotNull() {
        "Rest API is not null".LogI("RestAPI")
    }

    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}