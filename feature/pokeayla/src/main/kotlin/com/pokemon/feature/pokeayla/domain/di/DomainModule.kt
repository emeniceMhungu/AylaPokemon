package com.pokemon.feature.pokeayla.domain.di

import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonDetailsMapper
import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonDetailsMapperImpl
import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonListMapper
import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonListMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DomainModule {
    @Binds
    fun bindGetPokemonListMapper(
        getPokemonListMapperImpl: GetPokemonListMapperImpl
    ): GetPokemonListMapper

    @Binds
    fun bindGetPokemonDetailsMapper(
        getPokemonDetailsMapperImpl: GetPokemonDetailsMapperImpl
    ): GetPokemonDetailsMapper
}