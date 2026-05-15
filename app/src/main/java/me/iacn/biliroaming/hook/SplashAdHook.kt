package me.iacn.biliroaming.hook

import me.iacn.biliroaming.BiliBiliPackage.Companion.instance
import me.iacn.biliroaming.utils.Log
import me.iacn.biliroaming.utils.hookMethod
import me.iacn.biliroaming.utils.sPrefs

class SplashAdHook(classLoader: ClassLoader) : BaseHook(classLoader) {
    override fun startHook() {
        if (!sPrefs.getBoolean("skip_splash_ad", false)) return

        instance.splashAdClass?.hookMethod(instance.showSplash(), Boolean::class.javaPrimitiveType, Boolean::class.javaPrimitiveType) { chain ->
            Log.d("SplashAdHook: Skipped splash ad")
            chain.proceed()
            true
        }
    }
}
