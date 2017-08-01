package com.base.project.utils

import android.app.Dialog
import android.content.Context
import com.base.project.R

/**
 * This DialogFactory use for createSync the material dialog which have private constructor
 * that require fragment [context] and [dialog] instance
 */
class DialogFactory private constructor(private val context: Context,
										private val dialog: Dialog?) {

	private var successDialog: DialogFactory? = null
	private var errorDialog: DialogFactory? = null

	/**
	 * Show [dialog] from [dialog] instance which createSync from [Builder]
	 */
	fun show() {
		dialog?.let(Dialog::show)
	}

	/**
	 * Dismiss [dialog] from [dialog] instance which createSync from [Builder]
	 */
	fun dismiss() {
		dialog?.let(Dialog::dismiss)
	}

	/**
	 * Dismiss [dialog] from [dialog] instance which createSync from [Builder]
	 * and createSync new successDialog instance (if not have createSync yet) then apply it to view
	 */
	fun complete() {
		dialog?.let {
			if (it.isShowing) {
				it.dismiss()
			}
			if (null == successDialog) {
				successDialog = DialogFactory.Builder(context).createDefaultDialog(R.string.dialog_complete).build()
			}
			successDialog?.let(DialogFactory::show)
		}
	}

	/**
	 * Dismiss [dialog] from [dialog] instance which createSync from [Builder]
	 * and createSync new errorDialog instance (if not have createSync yet) then apply it to view
	 */
	fun error() {
		dialog?.let {
			if (it.isShowing) {
				it.dismiss()
			}
			if (null == errorDialog) {
				errorDialog = DialogFactory.Builder(context).createDefaultDialog(R.string.dialog_error).build()
			}
			errorDialog?.let(DialogFactory::show)
		}
	}

	/**
	 * Inner Builder class in [DialogFactory] which require [context] as parameter to instantiate
	 * [MaterialDialog] instance

	 * @param context the fragment or view context
	 */
	class Builder constructor(private val context: Context) {
		private var dialog: Dialog? = null

		fun build(): DialogFactory {
			return DialogFactory(context, dialog)
		}

		fun createDefaultDialog(title: String): DialogFactory.Builder {
			if (null == dialog) {
				dialog = Dialog(context)
				dialog?.setTitle(title)
			}
			return this
		}

		fun createDefaultDialog(title: Int): DialogFactory.Builder {
			if (null == dialog) {
				dialog = Dialog(context)
				dialog?.setTitle(title)
			}
			return this
		}
	}
}