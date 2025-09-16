package io.github.yuu0922.lrinsight.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.yuu0922.lrinsight.data.repository.LericoRepository
import io.github.yuu0922.lrinsight.domain.repository.ILericoRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLericoRepository(
        impl: LericoRepository
    ): ILericoRepository
}