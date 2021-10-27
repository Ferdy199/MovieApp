package com.dicoding.movieapp.core.core_di

import android.content.Context
import androidx.room.Room
import com.dicoding.movieapp.core.BuildConfig
import com.dicoding.movieapp.core.local.room.MovieDao
import com.dicoding.movieapp.core.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(context: Context): MovieDatabase{
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSPHRASE.toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(context, MovieDatabase::class.java, BuildConfig.DATABASE_NAME).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()
}