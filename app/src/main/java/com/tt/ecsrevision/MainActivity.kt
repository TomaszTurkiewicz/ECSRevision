package com.tt.ecsrevision

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import com.tt.ecsrevision.ui.theme.ECSRevisionTheme
import com.tt.ecsrevision.viewmodels.AppViewModel
import com.tt.ecsrevision.viewmodels.AppViewModelFactory
import java.util.concurrent.atomic.AtomicBoolean

const val TEST = true

class MainActivity : ComponentActivity() {

    private val viewModel:AppViewModel by viewModels{
        AppViewModelFactory(
            (this.application as ECSApplication).database.questionDao(),
            (this.application as ECSApplication).database.testDao(),
            (this.application as ECSApplication).database.passMarkDao(),
            (this.application as ECSApplication).database.testTimeDao()
        )
    }

    private var mInterstitialAd:InterstitialAd? = null
    private var mRewardedAd: RewardedAd? = null

    private lateinit var consentInformation: ConsentInformation
    // Use an atomic boolean to initialize the Google Mobile Ads SDK and load ads once.
    private var isMobileAdsInitializeCalled = AtomicBoolean(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECSRevisionTheme {
                ECSRevisionApp(
                    viewModel = viewModel,
                    activity = this)
            }
        }

        requestConsentForm()
    }

    private fun requestConsentForm() {
        val params = ConsentRequestParameters
            .Builder()
            .setTagForUnderAgeOfConsent(false)
            .build()

        consentInformation = UserMessagingPlatform.getConsentInformation(this)
//        consentInformation.reset()
        consentInformation.requestConsentInfoUpdate(
            this,
            params,
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                    this@MainActivity
                ) { loadAndShowError ->
                    // Consent gathering failed.
                    Log.w(
                        ContentValues.TAG, String.format(
                            "%s: %s",
                            loadAndShowError?.errorCode,
                            loadAndShowError?.message
                        )
                    )

                    // Consent has been gathered.
                    if (consentInformation.canRequestAds()) {
                        initializeMobileAdsSdk()
                    }
                }
            },
            {
                    requestConsentError ->
                // Consent gathering failed.
                Log.w(
                    ContentValues.TAG, String.format("%s: %s",
                    requestConsentError.errorCode,
                    requestConsentError.message))
            })

        // Check if you can initialize the Google Mobile Ads SDK in parallel
        // while checking for new consent information. Consent obtained in
        // the previous session can be used to request ads.
        if (consentInformation.canRequestAds()) {
            initializeMobileAdsSdk()
        }
    }

    private fun initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.get()) {
            return
        }
        isMobileAdsInitializeCalled.set(true)

        // Initialize the Google Mobile Ads SDK.
        MobileAds.initialize(this)

    }

    fun loadInterstitialAd(){
        val interstitialAdId:String = if(TEST) this.getString(R.string.test_interstitial_ad) else this.getString(R.string.admob_interstitial_ad)
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,interstitialAdId,adRequest, object : InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                mInterstitialAd = interstitialAd
            }
        })
    }

    fun loadRewardedAd(){
        val rewardedAdId: String = if(TEST) this.getString(R.string.test_rewarded_ad) else this.getString(R.string.admob_rewarded_ad)
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(this,rewardedAdId,adRequest, object : RewardedAdLoadCallback(){
            override fun onAdFailedToLoad(error: LoadAdError) {
                super.onAdFailedToLoad(error)
                mRewardedAd = null
            }

            override fun onAdLoaded(ad: RewardedAd) {
                super.onAdLoaded(ad)
                mRewardedAd = ad
                viewModel.rewardedApLoaded()
            }
        })
    }

    fun showRewardedAd(){
        mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback(){

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
                mRewardedAd = null
            }
        }

        mRewardedAd?.let {
            it.show(this){
                mRewardedAd = null
                viewModel.rewardedAdWatched()
            }
        }

    }

    fun showInterstitialAd(){
        mInterstitialAd?.let {ad ->
            ad.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    mInterstitialAd = null
                }
            }
            ad.show(this)
        }
    }

    fun interstitialAdIsLoaded():Boolean{
        return mInterstitialAd!=null
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//
//    ECSRevisionTheme {
//        ECSRevisionApp()
//    }
//}


//todo use ksp instead kapt !!!!