package keddit.com.egn.keddit.ui.worker

import io.reactivex.Observable
import keddit.com.egn.keddit.ui.adapter.model.RedditNewsItem

/**
 * Created by Mai Huu Hanh on 7/8/18.
 */
class NewsManager {
    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create { subcriber ->
            val news = mutableListOf<RedditNewsItem>()
            for (i in 1..10) {
                news.add(RedditNewsItem("author$i", "title$i", i, 1457207701L - i * 200,
                        "http://lorempixel.com/200/200/technics/$i", "url"))
            }
            subcriber.onNext(news)
            subcriber.onComplete()
        }
    }
}