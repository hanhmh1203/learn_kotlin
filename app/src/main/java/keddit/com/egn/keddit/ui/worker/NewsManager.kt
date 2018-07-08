package keddit.com.egn.keddit.ui.worker

import io.reactivex.Observable
import keddit.com.egn.keddit.ui.adapter.model.RedditNews
import keddit.com.egn.keddit.ui.adapter.model.RedditNewsItem

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
class NewsManager(private val api: RestAPI = RestAPI()) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create { subcriber ->
            val news = mutableListOf<RedditNewsItem>()
            //yummi data
//            for (i in 1..10) {
//                news.add(RedditNewsItem("author$i", "title$i", i, 1457207701L - i * 200,
//                        "http://lorempixel.com/200/200/technics/$i", "url"))
//            }
            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }
                val redditNews = RedditNews(dataResponse.before ?: "", dataResponse.after
                        ?: "", news)
                subcriber.onNext(redditNews)
                subcriber.onComplete()
            } else {
                subcriber.onError(Throwable(response.message()))
            }
        }
    }
}