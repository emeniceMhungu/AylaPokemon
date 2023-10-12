package com.pokemon.feature.pokeayla.domain.mapper

import com.pokemon.feature.pokeayla.data.model.responses.PokemonDetailsResponse
import com.pokemon.feature.pokeayla.data.model.responses.SpeciesDetailsResponse
import com.pokemon.feature.pokeayla.domain.model.BaseResult
import com.pokemon.feature.pokeayla.domain.model.Pokemon
import com.pokemon.feature.pokeayla.domain.model.PokemonDetailsResult
import com.pokemon.feature.pokeayla.domain.model.createImageUrl
import com.pokemon.feature.pokeayla.domain.model.mapToColor
import com.pokemon.feature.pokeayla.domain.usecase.GetPokemonDetailsUseCase
import com.slack.eithernet.ApiResult
import timber.log.Timber
import java.util.Locale

interface GetPokemonDetailsMapper {
    fun mapAsResult(
        detailsResponse: ApiResult<PokemonDetailsResponse, Any>,
        speciesDetails: ApiResult<SpeciesDetailsResponse, Any>
    ): PokemonDetailsResult

}

class GetPokemonDetailsMapperImpl : GetPokemonDetailsMapper {
    override fun mapAsResult(
        detailsResponse: ApiResult<PokemonDetailsResponse, Any>,
        speciesResponse: ApiResult<SpeciesDetailsResponse, Any>
    ): PokemonDetailsResult {
        when (detailsResponse) {
            is ApiResult.Success -> {
                when (speciesResponse) {
                    is ApiResult.Success -> {
                        val inputDetails = detailsResponse.value
                        val inputSpecies = speciesResponse.value

                        val pokemonMapped = Pokemon(
                            id = inputDetails.id.toString(),
                            abilities = inputDetails.abilities.map {
                                Pair(
                                    it.ability.name,
                                    it.isHidden
                                )
                            },
                            stats = inputDetails.stats.map { Pair(it.stat.name, it.baseStat) },
                            imageUrl = createImageUrl(inputDetails.id.toString(), true),
                            about = inputSpecies.flavorTextEntries
                                .firstOrNull {
                                    it.language.name
                                        .contentEquals(Locale.ENGLISH.language, true)

                                }?.flavorText
                                ?.replace("\n", " ").orEmpty(),
                            height = (inputDetails.height / 10.0).toString(),
                            weight = (inputDetails.weight / 10.0).toString(),
                            types = inputDetails.types.map { it.type.name },
                            pokemonColor = mapToColor(inputSpecies.color.name),
                            name = inputDetails.name
                        )
                        return BaseResult.Success(pokemonMapped)
                    }

                    is ApiResult.Failure.NetworkFailure -> {
                        return BaseResult.Failure(
                            GetPokemonDetailsUseCase.Errors.NetworkError
                        )
                    }

                    is ApiResult.Failure.UnknownFailure -> {
                        return BaseResult.Failure(
                            GetPokemonDetailsUseCase.Errors.UnknownError
                        )
                    }

                    is ApiResult.Failure.HttpFailure -> {
                        return BaseResult.Failure(
                            GetPokemonDetailsUseCase.Errors.HttpError(speciesResponse.error.toString())
                        )
                    }

                    is ApiResult.Failure.ApiFailure -> {
                        Timber.e(speciesResponse.error as? Throwable)
                        return BaseResult.Failure(
                            GetPokemonDetailsUseCase.Errors.BadRequestError
                        )
                    }
                }
            }

            is ApiResult.Failure.NetworkFailure -> {
                return BaseResult.Failure(
                    GetPokemonDetailsUseCase.Errors.NetworkError
                )
            }

            is ApiResult.Failure.UnknownFailure -> {
                return BaseResult.Failure(
                    GetPokemonDetailsUseCase.Errors.UnknownError
                )
            }

            is ApiResult.Failure.HttpFailure -> {
                return BaseResult.Failure(
                    GetPokemonDetailsUseCase.Errors.HttpError(detailsResponse.error.toString())
                )
            }

            is ApiResult.Failure.ApiFailure -> {
                Timber.e(detailsResponse.error.toString())
                return BaseResult.Failure(
                    GetPokemonDetailsUseCase.Errors.BadRequestError
                )
            }
        }
    }
}