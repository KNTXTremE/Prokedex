package prokedex.com.xtreme.prokedex.PokeApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokemonRequest> obtainListPokemon(@Query("limit") int limit,@Query("offset") int offset);

    @GET("pokemon")
    Call<PokemonDescRequest> obtainListPokemonDesc(@Query("") int pokemonNumber);
}
