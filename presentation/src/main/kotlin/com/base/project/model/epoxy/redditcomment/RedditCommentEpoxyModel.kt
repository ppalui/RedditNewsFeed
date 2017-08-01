package com.base.project.model.epoxy.redditcomment

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.base.project.R
import com.base.project.model.RedditCommentModel
import java.text.SimpleDateFormat
import java.util.*

@EpoxyModelClass (layout = R.layout.row_epoxy_comment)
abstract class RedditCommentEpoxyModel : EpoxyModelWithHolder<RedditCommentEpoxyModel.RedditCommentViewHolder>() {

	@JvmField  @EpoxyAttribute protected var model: RedditCommentModel? = null

	override fun bind(holder: RedditCommentViewHolder) {
		val dateTime = SimpleDateFormat("hh:mm:ss aa")
		dateTime.timeZone = TimeZone.getTimeZone("GMT+7")

		holder.message.text = model?.message
		holder.author.text = model?.author
		holder.date.text = dateTime.format(Date(model?.createdTime ?: 0 * 1000L))
	}

	class RedditCommentViewHolder : EpoxyHolder() {
		lateinit var message: TextView
		lateinit var author: TextView
		lateinit var date: TextView
		override fun bindView(itemView: View) {
			message = itemView.findViewById(R.id.textViewMessage) as TextView
			author = itemView.findViewById(R.id.textViewAuthor) as TextView
			date = itemView.findViewById(R.id.textViewDate) as TextView
		}
	}
}
