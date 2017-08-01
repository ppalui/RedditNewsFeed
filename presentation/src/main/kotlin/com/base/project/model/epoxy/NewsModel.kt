package com.base.project.model.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.base.project.R
import com.base.project.extensions.allias.NewsItemClickListener
import com.base.project.extensions.allias.NewsItemCommentsClickListener
import com.base.project.extensions.allias.NewsItemShareClickListener
import com.base.project.model.RedditNewsModel
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@EpoxyModelClass(layout = R.layout.row_news)
abstract class NewsModel : EpoxyModelWithHolder<NewsModel.ViewHolder>() {

	@JvmField @EpoxyAttribute protected var model: RedditNewsModel? = null
	@JvmField @EpoxyAttribute protected var commentText: String = ""
	@JvmField @EpoxyAttribute protected var authorText: String = ""

	@JvmField @EpoxyAttribute protected var newsItemClickListener: NewsItemClickListener? = null
	@JvmField @EpoxyAttribute protected var newsItemCommentsClickListener: NewsItemCommentsClickListener? = null
	@JvmField @EpoxyAttribute protected var newsItemShareClickListener: NewsItemShareClickListener? = null

	override fun onViewAttachedToWindow(holder: ViewHolder) {
		super.onViewAttachedToWindow(holder)
		with(holder) {
			model?.let { model ->
				val dateTime = SimpleDateFormat("hh:mm:ss aa")
				dateTime.timeZone = TimeZone.getTimeZone("GMT+7")
				val date = Date(model.redditNewData?.created ?: 0 * 1000L)

				title.text = model.redditNewData?.title
				author.text = authorText
				timeCreate.text = dateTime.format(date)
				comment.text = commentText
				imageNews.maxWidth = model.redditNewData!!.thumbnail_width

				Glide.with(imageNews.context)
					.load(model.redditNewData.thumbnail)
					.centerCrop()
					.error(R.drawable.ic_reddit)
					.placeholder(R.color.primary_gray)
					.into(imageNews)

				layout.setOnClickListener { newsItemClickListener?.invoke(model.redditNewData.url, model.redditNewData.title) }
				comment.setOnClickListener { newsItemCommentsClickListener?.invoke(model.redditNewData.id) }
				share.setOnClickListener { newsItemShareClickListener?.invoke(model.redditNewData.id, model.redditNewData.permalink, model.redditNewData.title) }
			}
		}
	}

	inner class ViewHolder : EpoxyHolder() {

		lateinit var layout: View

		lateinit var title: TextView
		lateinit var imageNews: ImageView
		lateinit var author: TextView
		lateinit var timeCreate: TextView
		lateinit var comment: TextView
		lateinit var share: TextView

		override fun bindView(itemView: View) {
			layout = itemView.findViewById(R.id.layoutNews)
			title = itemView.findViewById(R.id.textViewNewsTitle) as TextView
			imageNews = itemView.findViewById(R.id.imageView) as ImageView
			author = itemView.findViewById(R.id.textViewAuthor) as TextView
			timeCreate = itemView.findViewById(R.id.textViewTimeCreated) as TextView
			comment = itemView.findViewById(R.id.textViewComments) as TextView
			share = itemView.findViewById(R.id.textViewShare) as TextView
		}
	}

}