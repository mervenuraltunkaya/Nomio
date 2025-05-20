package com.merve.nomio.di

import android.content.Context
import androidx.room.Room
import com.merve.nomio.data.datasource.SepetDataSource
import com.merve.nomio.data.datasource.YemeklerDataSource
import com.merve.nomio.data.repo.SepetRepository
import com.merve.nomio.data.repo.YemeklerRepository
import com.merve.nomio.retrofit.ApiUtils
import com.merve.nomio.retrofit.YemeklerDao
import com.merve.nomio.room.AciklamaVeritabani
import com.merve.nomio.room.YemekAciklamaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAciklamaDatabase(@ApplicationContext context: Context):AciklamaVeritabani{
        return Room.databaseBuilder(
            context, AciklamaVeritabani::class.java,"nomio.sqlite")
            .createFromAsset("nomio.sqlite")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideYemekAciklamaDao(db: AciklamaVeritabani): YemekAciklamaDao{
        return db.yemekAciklamaDao()
    }

}