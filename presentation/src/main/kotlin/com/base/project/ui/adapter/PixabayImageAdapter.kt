package com.base.project.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.project.R
import com.base.project.model.PixabayImageSourceModel
import com.bumptech.glide.Glide
import com.navik.multiplestate.MultipleStateAdapter
import com.navik.multiplestate.base.AdapterViewHolder
import com.navik.multiplestate.base.AdapterViewModel
import kotlinx.android.synthetic.main.row_image.view.*

class PixabayImageAdapter : MultipleStateAdapter() {
	companion object {
		val VIEW_TYPE_PIXABAY_IMAGE = 0
	}

	@Suppress("UNCHECKED_CAST")
	override fun onCreateMultipleViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): AdapterViewHolder<AdapterViewModel> {
		val view = inflater.inflate(R.layout.row_image, parent, false)
		return ImageViewHolder(view) as AdapterViewHolder<AdapterViewModel>
	}

	override fun onBindViewHolder(holder: AdapterViewHolder<AdapterViewModel>, item: AdapterViewModel?, position: Int) {
		item?.let { holder.bind(item) }
	}

	inner class ImageViewHolder(itemView: View) : AdapterViewHolder<ImageViewModel>(itemView) {
		override fun bind(item: ImageViewModel) {
			Glide.with(itemView.context)
				.load(item.imageSource.previewURL)
				.centerCrop()
				.error(R.color.accent)
				.placeholder(R.color.primary_gray)
				.into(itemView.imageViewPixabay)
		}
	}

	class ImageViewModel(val imageSource: PixabayImageSourceModel) : AdapterViewModel {
		override fun type(): Int = VIEW_TYPE_PIXABAY_IMAGE
	}
}