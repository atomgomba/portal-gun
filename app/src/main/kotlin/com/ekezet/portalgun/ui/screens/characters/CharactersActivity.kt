package com.ekezet.portalgun.ui.screens.characters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.ekezet.portalgun.base.BaseActivity
import com.ekezet.portalgun.ui.screens.characters.layouts.CharactersScreen
import javax.inject.Inject

class CharactersActivity : BaseActivity() {

    @Inject internal lateinit var viewModel: CharactersContract.ViewModel
    @Inject internal lateinit var router: CharactersContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CharactersScreen(viewModel, router)
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, CharactersActivity::class.java)
    }
}
