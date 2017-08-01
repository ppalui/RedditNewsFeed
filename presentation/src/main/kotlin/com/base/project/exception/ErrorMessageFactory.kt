package com.base.project.exception

import android.content.Context
import com.base.project.R
import com.navik.data.exception.NetworkConnectionException
import com.navik.data.exception.http.HttpDomainException
import com.navik.data.exception.http.UnauthorizedException
import timber.log.Timber

/**
 * Factory used to createSync error messages from an Exception as a condition.
 */
class ErrorMessageFactory {

	companion object {
		/**
		 * Creates a String representing an error message.
		 * @param context Context needed to retrieve string resources.
		 * @param exception An exception used as a condition to retrieve the correct error message.
		 * @return [String] an error message.
		 */
		@JvmStatic
		fun create(context: Context?,
				   exception: Exception?): String {

			Timber.e(exception?.message)
			return when (exception) {
				is NetworkConnectionException -> context?.getString(R.string.exception_message_no_connection)
				is HttpDomainException        -> context?.getString(R.string.exception_message_http_error)
				is UnauthorizedException      -> context?.getString(R.string.exception_message_unauthorized)
				else                          -> context?.getString(R.string.exception_message_generic)
			} ?: ""
		}
	}
}