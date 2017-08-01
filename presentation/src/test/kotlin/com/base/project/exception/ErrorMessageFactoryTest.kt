package com.base.project.exception

import android.content.Context
import com.base.project.R
import com.navik.data.exception.NetworkConnectionException
import com.navik.data.exception.http.HttpDomainException
import com.navik.data.exception.http.UnauthorizedException
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class ErrorMessageFactoryTest : Spek({

	val networkConnectionExceptionMessage = "exception_message_no_connection"
	val httpDomainExceptionMessage = "exception_message_http_error"
	val unauthorizedExceptionMessage = "exception_message_unauthorized"
	val otherExceptionMessage = "exception_message_generic"

	val mockContext: Context = mock()
	whenever(mockContext.getString(R.string.exception_message_no_connection)).thenReturn(networkConnectionExceptionMessage)
	whenever(mockContext.getString(R.string.exception_message_http_error)).thenReturn(httpDomainExceptionMessage)
	whenever(mockContext.getString(R.string.exception_message_unauthorized)).thenReturn(unauthorizedExceptionMessage)
	whenever(mockContext.getString(R.string.exception_message_generic)).thenReturn(otherExceptionMessage)

	describe("call create") {

		given("NetworkConnectionException") {
			val actualMessage = ErrorMessageFactory.create(mockContext, NetworkConnectionException())

			it("should return correct message") {
				assertEquals(actualMessage, networkConnectionExceptionMessage)
			}
		}

		given("HttpDomainException") {
			val actualMessage = ErrorMessageFactory.create(mockContext, HttpDomainException())

			it("should return correct message") {
				assertEquals(actualMessage, httpDomainExceptionMessage)
			}
		}

		given("UnauthorizedException") {
			val actualMessage = ErrorMessageFactory.create(mockContext, UnauthorizedException())

			it("should return correct message") {
				assertEquals(actualMessage, unauthorizedExceptionMessage)
			}
		}

		given("IllegalArgumentException") {
			val actualMessage = ErrorMessageFactory.create(mockContext, IllegalArgumentException())

			it("should return correct message") {
				assertEquals(actualMessage, otherExceptionMessage)
			}
		}
	}
})