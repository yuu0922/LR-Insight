package io.github.yuu0922.lrinsight.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.yuu0922.lrinsight.prefs.AppPrefs

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAppPrefs(@ApplicationContext context: Context): AppPrefs {
        return AppPrefs(context)
    }
}