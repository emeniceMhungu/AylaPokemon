package com.pokemon.feature.pokeayla.domain.usecase

import com.pokemon.feature.pokeayla.data.repository.PokemonRepository
import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonListMapper
import com.pokemon.feature.pokeayla.domain.model.BaseResult
import com.pokemon.network.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonListMapper: GetPokemonListMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {

    sealed interface Errors {
        data object UnknownError : Errors
        data object NetworkError : Errors
        data class HttpError(val error: String) : Errors
    }

    fun execute(count: Int) = flow {
        emit(BaseResult.Loading())

        val response = pokemonRepository.retrievePokemonList(count)

        val result = pokemonListMapper.mapAsResult(response)

        emit(result)
    }.catch {
        Timber.e(it)
        it.printStackTrace()
        emit(BaseResult.Failure(Errors.UnknownError))
    }.flowOn(dispatcher)
}