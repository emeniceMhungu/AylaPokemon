package com.pokemon.feature.pokeayla.domain.usecase

import app.cash.turbine.test
import com.hannesdorfmann.instantiator.instance
import com.pokemon.feature.pokeayla.data.model.responses.PokemonDetailsResponse
import com.pokemon.feature.pokeayla.data.model.responses.SpeciesDetailsResponse
import com.pokemon.feature.pokeayla.data.repository.PokemonRepository
import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonDetailsMapperImpl
import com.pokemon.feature.pokeayla.domain.model.BaseResult
import com.slack.eithernet.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class GetPokemonDetailsUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository: PokemonRepository = mockk(relaxed = true)

    private val getPokemonDetailsMapper = GetPokemonDetailsMapperImpl()

    private lateinit var getPokemonDetailsUseCase: GetPokemonDetailsUseCase

    private val detailsResponse = instance<PokemonDetailsResponse>()
    private val speciesResponse = instance<SpeciesDetailsResponse>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPokemonDetailsUseCase = GetPokemonDetailsUseCase(
            pokemonRepository = repository,
            getPokemonDetailsMapper = getPokemonDetailsMapper,
            dispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getting pokemon details and species is successful`() = runTest {
        coEvery {
            repository.getPokemonDetails(any())
        } returns ApiResult.success(detailsResponse)

        coEvery {
            repository.getSpeciesDetails(any())
        } returns ApiResult.success(speciesResponse)

        getPokemonDetailsUseCase.execute("21").test {
            assert(awaitItem() is BaseResult.Loading)

            val result = this.awaitItem()

            val successResponse = result as? BaseResult.Success

            val data = successResponse?.data

            assert(successResponse is BaseResult.Success)
            assertEquals(detailsResponse.abilities.size, data?.abilities?.size)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getting details and species returns valid error if either has a failure`() = runTest {
        coEvery {
            repository.getSpeciesDetails(any())
        }.returns(ApiResult.success(speciesResponse))

        coEvery {
            repository.getPokemonDetails(any())
        } returns ApiResult.httpFailure(
            code = HttpURLConnection.HTTP_NOT_FOUND,
            error = "Not found"
        )

        getPokemonDetailsUseCase.execute("24").test {
            assert(awaitItem() is BaseResult.Loading)

            val result = this.awaitItem()

            val errorResponse = result as? BaseResult.Failure
            val httpError = errorResponse?.error as? GetPokemonDetailsUseCase.Errors.HttpError

            assert(errorResponse is BaseResult.Failure)
            assert(httpError is GetPokemonDetailsUseCase.Errors.HttpError)
            assert(httpError?.errorMsg == "Not found")

            cancelAndIgnoreRemainingEvents()
        }
    }
}