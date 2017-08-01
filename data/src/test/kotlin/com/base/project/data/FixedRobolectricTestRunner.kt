package com.base.project.data

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.manifest.AndroidManifest
import org.robolectric.res.FileFsFile
import java.io.File
import kotlin.reflect.KClass

class FixedRobolectricTestRunner(testClass: Class<*>) : RobolectricTestRunner(testClass) {

	override fun getAppManifest(config: Config): AndroidManifest {
		val appManifest = super.getAppManifest(config)
		var androidManifestFile = appManifest.androidManifestFile

		if (androidManifestFile.exists()) {
			return appManifest
		}
		else {
			androidManifestFile = FileFsFile.from(getModuleRootPath(config),
				appManifest.androidManifestFile.path.replace("manifests" + File.separator + "full", "manifests" + File.separator + "aapt"))
			return AndroidManifest(androidManifestFile, appManifest.resDirectory, appManifest.assetsDirectory)
		}
	}

	private fun getModuleRootPath(config: Config): String {
		val constants: KClass<*> = config.constants
		val moduleRoot = constants.java.getResource("").toString().replace("file:", "")
		return moduleRoot.substring(0, moduleRoot.indexOf("/build"))
	}
}