package com.merve.nomio.di

import com.merve.nomio.data.datasource.SepetDataSource
import com.merve.nomio.data.datasource.YemeklerDataSource
import com.merve.nomio.data.repo.SepetRepository
import com.merve.nomio.data.repo.YemeklerRepository
import com.merve.nomio.retrofit.ApiUtils
import com.merve.nomio.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerRepository(yemeklerDataSource: YemeklerDataSource): YemeklerRepository{
        return YemeklerRepository(yemeklerDataSource)
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(yemeklerDao: YemeklerDao): YemeklerDataSource{
        return YemeklerDataSource(yemeklerDao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao():YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideSepetRepository(sepetDataSource: SepetDataSource): SepetRepository {
        return SepetRepository(sepetDataSource)
    }

    @Provides
    @Singleton
    fun provideSepetDataSource(yemeklerDao: YemeklerDao): SepetDataSource {
        return SepetDataSource(yemeklerDao)
    }

}