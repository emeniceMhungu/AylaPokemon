package com.pokemon.feature.pokeayla.data.repository

import com.hannesdorfmann.instantiator.instance
import com.pokemon.feature.pokeayla.data.api.PokeAylaAPI
import com.pokemon.feature.pokeayla.data.model.responses.PokemonCharactersResponse
import com.pokemon.feature.pokeayla.data.model.responses.PokemonDetailsResponse
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

class PokemonRepositoryTest {
    private val testDispatcher = StandardTestDispatcher()
    private var pokemonApi = mockk<PokeAylaAPI>(relaxed = true)

    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        pokemonRepository = PokemonRepositoryImpl(pokemonApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `retrievePokemonList returns returns pokemon character list successfully`() = runTest {
        val pokemonCharacterList = instance<PokemonCharactersResponse>()
        val result = ApiResult.success(pokemonCharacterList)
        coEvery {
            pokemonApi.getPokemonList(
                limit = any(),
                offset = any()
            )
        } returns result

        val successResponse = pokemonRepository.retrievePokemonList(5)

        val successResult = successResponse as? ApiResult.Success

        assert(successResult is ApiResult.Success)
        assertEquals(pokemonCharacterList, successResult?.value)
    }

    @Test
    fun `retrievePokemonList returns valid error response`() = runTest {
        val code = 404
        val message = "Not found"
        val result = ApiResult.httpFailure(
            code = code,
            error = message
        )

        coEvery {
            pokemonApi.getPokemonList(
                limit = any(),
                offset = any()
            )
        } returns result

        val failureResponse = pokemonRepository.retrievePokemonList(5)

        val failureResult = failureResponse as? ApiResult.Failure.HttpFailure

        assert(failureResult is ApiResult.Failure.HttpFailure)
        assertEquals(code, failureResult?.code)
        assertEquals(message, failureResult?.error?.toString())
    }

    @Test
    fun `getPokemonDetails returns valid successful response`() = runTest {
        val pokemonDetailsResponse = instance<PokemonDetailsResponse>()
        val result = ApiResult.success(pokemonDetailsResponse)
        coEvery {
            pokemonApi.getPokemonDetails(any())
        } returns result

        val successResult = pokemonRepository.getPokemonDetails("10")

        val successResponse = successResult as? ApiResult.Success

        assert(successResponse is ApiResult.Success)
        assertEquals(pokemonDetailsResponse, successResponse?.value)

    }

    @Test
    fun `getPokemonDetails returns valid error response`() = runTest {
        val code = 404
        val message = "Not found"
        val result = ApiResult.httpFailure(
            code = code,
            error = message
        )

        coEvery {
            pokemonApi.getPokemonDetails(any())
        } returns result

        val failureResponse = pokemonRepository.getPokemonDetails("10")

        val failureResult = failureResponse as? ApiResult.Failure.HttpFailure

        assert(failureResult is ApiResult.Failure.HttpFailure)
        assertEquals(code, failureResult?.code)
        assertEquals(message, failureResult?.error?.toString())
    }
}