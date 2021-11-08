package com.ekezet.portalgun.ui.screens.characters

import android.content.Context
import androidx.core.app.ActivityOptionsCompat
import com.ekezet.portalgun.ui.screens.characterDetails.CharacterDetailsActivity
import javax.inject.Inject

internal class CharactersRouter @Inject constructor(
    private val context: Context,
) : CharactersContract.Router {

    override fun showCharacterDetails(id: Int) = with(context as CharactersActivity) {
        val intent = CharacterDetailsActivity.newIntent(this)
        val options = ActivityOptionsCompat.makeCustomAnimation(
            context,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }
}
