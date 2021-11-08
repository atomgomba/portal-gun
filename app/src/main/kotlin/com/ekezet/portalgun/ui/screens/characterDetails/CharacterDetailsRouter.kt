package com.ekezet.portalgun.ui.screens.characterDetails

import android.content.Context
import javax.inject.Inject

internal class CharacterDetailsRouter @Inject constructor(
    private val context: Context,
) : CharacterDetailsContract.Router {

    override fun finish() {
        (context as CharacterDetailsActivity).run {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
