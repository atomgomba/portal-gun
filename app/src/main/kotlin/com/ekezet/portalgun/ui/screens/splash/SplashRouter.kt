package com.ekezet.portalgun.ui.screens.splash

import android.content.Context
import javax.inject.Inject

internal class SplashRouter @Inject constructor(
    private val context: Context,
) : SplashContract.Router {

    override fun jumpToNextScreen() = with(context as SplashActivity) {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
