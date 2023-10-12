package com.pokemon.feature.pokeayla.data.di

import com.pokemon.feature.pokeayla.data.api.PokeAylaAPI
import com.pokemon.feature.pokeayla.data.repository.PokemonRepository
import com.pokemon.feature.pokeayla.data.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository = pokemonRepositoryImpl

    @Singleton
    @Provides
    fun providePokemonApi(
        retrofit: Retrofit.Builder
    ): PokeAylaAPI {
        return retrofit.build().create(PokeAylaAPI::class.java)
    }

}