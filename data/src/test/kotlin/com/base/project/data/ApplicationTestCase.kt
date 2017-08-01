package com.base.project.data

import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Base class for Robolectric data layer tests.
 * Inherit from this class to createSync a test.
 */
@RunWith(FixedRobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = ApplicationStub::class, sdk = intArrayOf(23))
abstract class ApplicationTestCase