package com.ekezet.portalgun.ui

import android.os.Bundle
import com.ekezet.portalgun.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject internal lateinit var router: MainContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.jumpToNextScreen()
    }
}
