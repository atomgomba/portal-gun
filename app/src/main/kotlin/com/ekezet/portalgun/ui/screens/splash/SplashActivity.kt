package com.ekezet.portalgun.ui.screens.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.ekezet.portalgun.base.BaseActivity
import com.ekezet.portalgun.ui.screens.splash.layouts.SplashScreen
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    @Inject internal lateinit var viewModel: SplashContract.ViewModel
    @Inject internal lateinit var router: SplashContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { SplashScreen(viewModel.isLoading.collectAsState(initial = false)) }

        loadStuff()
    }

    private fun loadStuff() = lifecycleScope.launch {
        // just to show buildId for a short while
        delay(DEFAULT_DELAY_MILLIS)

        viewModel.isLoading
            .combine(viewModel.lastError) { isLoading, lastError ->
                isLoading to lastError
            }
            .collect { (isLoading, lastError) ->
                if (isLoading) {
                    return@collect
                }

                if (lastError != null) {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        lastError.localizedMessage ?: "Error",
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                    return@collect
                }

                router.jumpToNextScreen()
            }
    }

    companion object {
        const val DEFAULT_DELAY_MILLIS = 2000L

        fun newIntent(context: Context) = Intent(context, SplashActivity::class.java)
    }
}
