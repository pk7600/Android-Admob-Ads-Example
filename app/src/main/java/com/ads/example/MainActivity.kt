package com.ads.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.ads.helper.InterstitialAdHelper
import com.example.app.ads.helper.InterstitialAdHelper.isShowInterstitialAd
import com.example.app.ads.helper.developerNeedLoadAdsOnce
import com.game.dreamteam.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InterstitialAdHelper.loadInterstitialAd(this@MainActivity)

        btn1.setOnClickListener {
            isShowInterstitialAd {

            }
        }

        btn2.setOnClickListener {
            developerNeedLoadAdsOnce = true
            InterstitialAdHelper.loadInterstitialAd(this@MainActivity){
                isShowInterstitialAd {

                }
            }
        }

        btn3.setOnClickListener {
            developerNeedLoadAdsOnce = false
            InterstitialAdHelper.loadInterstitialAd(this@MainActivity){
                isShowInterstitialAd {

                }
            }
        }
    }
}