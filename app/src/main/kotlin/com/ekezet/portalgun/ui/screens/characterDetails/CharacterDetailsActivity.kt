package com.ekezet.portalgun.ui.screens.characterDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.ekezet.portalgun.base.BaseActivity
import com.ekezet.portalgun.ui.screens.characterDetails.layouts.CharacterDetailsScreen
import javax.inject.Inject

class CharacterDetailsActivity : BaseActivity() {

    @Inject internal lateinit var viewModel: CharacterDetailsContract.ViewModel
    @Inject internal lateinit var router: CharacterDetailsContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CharacterDetailsScreen(viewModel)
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, CharacterDetailsActivity::class.java)
    }
}
