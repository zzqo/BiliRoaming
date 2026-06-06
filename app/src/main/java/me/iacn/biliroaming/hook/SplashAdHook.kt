package me.iacn.biliroaming.hook

import me.iacn.biliroaming.BiliBiliPackage.Companion.instance
import me.iacn.biliroaming.utils.Log
import me.iacn.biliroaming.utils.Weak
import me.iacn.biliroaming.utils.from
import me.iacn.biliroaming.utils.hookMethod
import me.iacn.biliroaming.utils.sPrefs

class SplashAdHook(classLoader: ClassLoader) : BaseHook(classLoader) {

    private val splashClass by Weak { "tv.danmaku.bili.ui.splash.ad.model.Splash" from mClassLoader }

    override fun startHook() {
        if (!sPrefs.getBoolean("skip_splash_ad", false)) return

        // 跳过品牌/活动开屏
        instance.splashAdClass?.hookMethod(instance.showBrandSplash(), Boolean::class.javaPrimitiveType, Boolean::class.javaPrimitiveType) { chain ->
            Log.d("SplashAdHook: Skipped brand splash ad")
            true
        }

        // 跳过商业广告
        instance.splashAdClass?.hookMethod(instance.showAdSplash(), splashClass, Boolean::class.javaPrimitiveType) { chain ->
            Log.d("SplashAdHook: Skipped ad splash ad")
            false
        }
    }
}
