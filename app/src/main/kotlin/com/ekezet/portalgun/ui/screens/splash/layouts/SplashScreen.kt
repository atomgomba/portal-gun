package com.ekezet.portalgun.ui.screens.splash.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekezet.portalgun.R
import com.ekezet.portalgun.ui.theme.SCREEN_PADDING
import com.ekezet.portalgun.ui.theme.White

@Composable internal fun SplashScreen(isLoading: State<Boolean>) {
    MaterialTheme {
        Box(modifier = Modifier.padding(SCREEN_PADDING)) {
            Text(
                text = "Build: " + stringResource(R.string.build_id),
                color = White,
            )
        }

        if (isLoading.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(128.dp),
                )
            }
        }
    }
}
