package com.pokemon.feature.pokeayla.domain.usecase

import app.cash.turbine.test
import com.hannesdorfmann.instantiator.instance
import com.pokemon.feature.pokeayla.data.model.responses.PokemonCharactersResponse
import com.pokemon.feature.pokeayla.data.repository.PokemonRepository
import com.pokemon.feature.pokeayla.domain.mapper.GetPokemonListMapperImpl
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
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class GetPokemonListUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val repository: PokemonRepository = mockk(relaxed = true)

    private val getPokemonListMapper = GetPokemonListMapperImpl()

    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPokemonListUseCase = GetPokemonListUseCase(
            pokemonRepository = repository,
            pokemonListMapper = getPokemonListMapper,
            dispatcher = testDispatcher
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `retrieve pokemon list is successful`() = runTest {
        val pokemonCharactersResponse = instance<PokemonCharactersResponse>()

        coEvery {
            repository.retrievePokemonList(any())
        } returns ApiResult.success(pokemonCharactersResponse)

        getPokemonListUseCase.execute(4).test {
            assert(awaitItem() is BaseResult.Loading)

            val result = awaitItem()

            val successResponse = result as? BaseResult.Success

            assert(successResponse is BaseResult.Success)

            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `retrieve pokemon list returns a valid error`() = runTest {
        coEvery {
            repository.retrievePokemonList(any())
        } returns ApiResult.httpFailure(
            code = HttpURLConnection.HTTP_NOT_FOUND,
            error = "Not found"
        )

        getPokemonListUseCase.execute(2).test {
            assert(awaitItem() is BaseResult.Loading)

            val result = awaitItem()

            val errorResponse = result as? BaseResult.Failure

            assert(errorResponse is BaseResult.Failure)

            cancelAndIgnoreRemainingEvents()
        }
    }
}