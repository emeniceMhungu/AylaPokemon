package com.pokemon.feature.pokeayla.domain.mapper

import com.pokemon.feature.pokeayla.data.model.responses.PokemonCharactersResponse
import com.pokemon.feature.pokeayla.domain.model.BaseResult
import com.pokemon.feature.pokeayla.domain.model.PokemonCharacterDomainModel
import com.pokemon.feature.pokeayla.domain.model.PokemonListResult
import com.pokemon.feature.pokeayla.domain.model.createImageUrl
import com.pokemon.feature.pokeayla.domain.model.getIdFromUrl
import com.pokemon.feature.pokeayla.domain.usecase.GetPokemonListUseCase
import com.slack.eithernet.ApiResult
import javax.inject.Inject

interface GetPokemonListMapper {
    fun mapAsResult(response: ApiResult<PokemonCharactersResponse, Any>):
            PokemonListResult
}

class GetPokemonListMapperImpl @Inject constructor() : GetPokemonListMapper {
    override fun mapAsResult(response: ApiResult<PokemonCharactersResponse, Any>):
            PokemonListResult {
        return when (response) {
            is ApiResult.Success -> {
                val listResult = response.value.results.map {
                    val id = it.url.getIdFromUrl()
                    PokemonCharacterDomainModel(
                        id = id,
                        pokemonName = it.name,
                        pokemonUrl = createImageUrl(id)
                    )
                }
                BaseResult.Success(listResult)
            }

            is ApiResult.Failure.NetworkFailure ->
                BaseResult.Failure(GetPokemonListUseCase.Errors.NetworkError)

            is ApiResult.Failure.UnknownFailure ->
                BaseResult.Failure(GetPokemonListUseCase.Errors.UnknownError)

            is ApiResult.Failure.HttpFailure ->
                BaseResult.Failure(
                    GetPokemonListUseCase.Errors.HttpError(
                        response.error.toString()
                    )
                )

            is ApiResult.Failure.ApiFailure ->
                BaseResult.Failure(GetPokemonListUseCase.Errors.NetworkError)
        }
    }
}