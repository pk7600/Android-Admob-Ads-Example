package com.ads.example

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import com.example.app.ads.helper.VasuAdsConfig
import com.example.app.ads.helper.openad.AppOpenApplication
import com.example.app.ads.helper.openad.AppOpenApplication.AppLifecycleListener
import com.facebook.FacebookSdk
import com.facebook.ads.AudienceNetworkAds
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainApplication : AppOpenApplication(), AppLifecycleListener {

    companion object {

        var mContext: Context? = null

    }

    override fun onCreate() {
        super.onCreate()

        mContext = this

        setAppLifecycleListener(this)

        // All Ad-Ids are Optional
        VasuAdsConfig.with(this)
            .isEnableOpenAd(false /* Default Value */) // Pass false if you don't need to show open ad in your project
            .setAdmobAppId(resources.getString(R.string.admob_app_id))
            .setAdmobBannerAdId(resources.getString(R.string.admob_bannerad_id))
            .setAdmobInterstitialAdId(resources.getString(R.string.admob_interstitialad_id))
            .setAdmobNativeAdvancedAdId(resources.getString(R.string.admob_nativead_id))
            .setAdmobOpenAdId(resources.getString(R.string.admob_openad_id))
            .initialize()

        initMobileAds()

        AudienceNetworkAds.initialize(this)
        FacebookSdk.sdkInitialize(mContext)
        FacebookSdk.setAutoInitEnabled(true)
        FacebookSdk.fullyInitialize()

        printHashKey(this)

    }

    private fun printHashKey(pContext: Context) {
        try {
            @SuppressLint("PackageManagerGetSignatures") val info =
                pContext.packageManager.getPackageInfo(
                    pContext.packageName,
                    PackageManager.GET_SIGNATURES
                )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey: String = String(Base64.encode(md.digest(), 0))
                Log.i("AppOpenApplication", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("AppOpenApplication", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("AppOpenApplication", "printHashKey()", e)
        }
    }

    override fun onResumeApp(fCurrentActivity: Activity): Boolean {
        return false
    }

}