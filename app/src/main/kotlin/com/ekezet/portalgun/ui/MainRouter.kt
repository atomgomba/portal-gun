package com.ekezet.portalgun.ui

import android.content.Context
import android.content.Intent
import com.ekezet.portalgun.ui.screens.characters.CharactersActivity
import com.ekezet.portalgun.ui.screens.splash.SplashActivity
import javax.inject.Inject

internal class MainRouter @Inject constructor(private val context: Context) : MainContract.Router {
    override fun jumpToNextScreen() {
        val charactersIntent = CharactersActivity.newIntent(context)
        charactersIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        context.startActivity(charactersIntent)

        val splashIntent = SplashActivity.newIntent(context)
        splashIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        context.startActivity(splashIntent)
    }
}
