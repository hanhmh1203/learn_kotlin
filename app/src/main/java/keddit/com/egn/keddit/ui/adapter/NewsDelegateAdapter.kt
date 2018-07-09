package keddit.com.egn.keddit.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import keddit.com.egn.keddit.R
import keddit.com.egn.keddit.commons.*
import keddit.com.egn.keddit.ui.adapter.commons.ViewType
import keddit.com.egn.keddit.ui.adapter.commons.ViewTypeDelegateAdapter
import keddit.com.egn.keddit.ui.adapter.model.RedditNewsItem
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter : ViewTypeDelegateAdapter {
    //    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
//        return TurnsViewHolder(parent)
//    }
    override fun onCreateViewHolder(parent: ViewGroup, expanableInterface: ExpanableInterface): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, position: Int) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem, position)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item)) {
        fun bind(item: RedditNewsItem, position: Int) = with(itemView) {
            img_thumbnail.loadImage(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
            img_thumbnail.setOnClickListener(View.OnClickListener { view ->
                "img click ${item.author}".LogI("onclick")
            })
        }
    }
}
