interface PokemonApi {
    companion object {
        private const val version = BuildConfig.ApiVersion //TODO: configure this
    }

    @GET("$version/pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): ApiResult<PokemonCharactersResponse, Any>

    @DecodeErrorBody
    @GET("$version/pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") pokemonName: String
    ): ApiResult<PokemonDetailsResponse, Any>

    @GET("$version/pokemon-species/{id}")
    suspend fun getSpeciesDetails(
        @Path("id") speciesId: String
    ): ApiResult<SpeciesDetailsResponse, Any>

}