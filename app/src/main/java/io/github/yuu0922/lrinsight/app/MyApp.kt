package io.github.yuu0922.lrinsight.app

import android.app.Application
import coil3.ImageLoader
import coil3.SingletonImageLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()

        SingletonImageLoader.setSafe { imageLoader }
    }
}