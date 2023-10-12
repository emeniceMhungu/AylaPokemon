package com.pokemon.feature.pokeayla.domain.usecase

import com.pokemon.feature.pokeayla.data.repository.PokemonRepository
import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonDetailsMapper
import com.pokemon.feature.pokeayla.domain.model.BaseResult
import com.pokemon.network.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val getPokemonDetailsMapper: GetPokemonDetailsMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {
    sealed class Errors {
        data object BadRequestError : Errors()
        data object NetworkError : Errors()
        data object UnknownError : Errors()
        data class HttpError(val errorMsg: String) : Errors()
    }

    fun execute(id: String) = flow {
        emit(BaseResult.Loading())

        val detailsResponse = pokemonRepository.getPokemonDetails(id)

        val speciesDetails = pokemonRepository.getSpeciesDetails(id)


        val result = getPokemonDetailsMapper.mapAsResult(detailsResponse, speciesDetails)

        emit(result)
    }.catch {
        it.printStackTrace()
        Timber.e(it)
        emit(BaseResult.Failure(Errors.UnknownError))
    }.flowOn(dispatcher)
}