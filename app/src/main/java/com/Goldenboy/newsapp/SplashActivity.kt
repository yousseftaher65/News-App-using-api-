package com.Goldenboy.newsapp

import android.content.Intent
import com.daimajia.androidanimations.library.Techniques
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

class SplashActivity : AwesomeSplash() {

    private var mInterstitialAd : InterstitialAd? = null

    override fun initSplash(configSplash: ConfigSplash?) {
        MobileAds.initialize(this) {}

        configSplash?.backgroundColor = R.color.blue
        configSplash?.animCircularRevealDuration = 2000
        configSplash?.revealFlagX = Flags.REVEAL_RIGHT
        configSplash?.revealFlagY = Flags.REVEAL_BOTTOM
        //Customize Logo
        configSplash?.logoSplash = R.drawable.earth
        configSplash?.animLogoSplashDuration = 2000
        configSplash?.animLogoSplashTechnique = Techniques.FadeIn

        //Customize Title
        configSplash?.titleSplash = "News App"
        configSplash?.titleTextColor = R.color.white
        configSplash?.titleTextSize = 30f
        configSplash?.animTitleDuration = 3000
        configSplash?.animTitleTechnique = Techniques.Flash

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-2280613919615700/3502010247", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })

    }

    override fun animationsFinished() {
        if(mInterstitialAd != null){

            mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    openActivity()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    openActivity()
                }

                override fun onAdShowedFullScreenContent() {
                    mInterstitialAd = null
                }
            }
        }else{
            openActivity() }
        mInterstitialAd?.show(this)
    }
    fun openActivity(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }

}