package com.kodilla.imdbmovie.client;

import com.kodilla.imdbmovie.domain.ImbdMovieDetailsDto;
import com.kodilla.imdbmovie.domain.ImdbMovieDto;
import com.kodilla.imdbmovie.domain.ResponseDtoImdb;
import com.kodilla.imdbmovie.domain.SearchResponseImdb;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IMBDClient {

    private final RestTemplate restTemplate;

    @Value("${imbd.api.endpoint.prod}")
    private String imbdApiEndpoint;

    @Value("${imbd.app.key}")
    private String imbdKey;

    public List<ImdbMovieDto> getMoviesImdb() {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"MostPopularMovies/" + imbdKey)
                .build().encode().toUri();

        ResponseDtoImdb imbdResponse = restTemplate.getForObject(url, ResponseDtoImdb.class);

        assert imbdResponse != null;
        return imbdResponse.getItems();
    }

    public ImbdMovieDetailsDto getMovieDetailsImbd(String movieId) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"Title/" + imbdKey + "/" + movieId)
                .build().encode().toUri();

        ImbdMovieDetailsDto movieDetailsDto = restTemplate.getForObject(url, ImbdMovieDetailsDto.class);

        return movieDetailsDto;
    }

    public List<ImdbMovieDto> searchMovies(String queryContent) {
        URI url = UriComponentsBuilder
                .fromHttpUrl(imbdApiEndpoint +"Search/" + imbdKey + "/" + queryContent)
                .build().encode().toUri();

        SearchResponseImdb responseDtoImdb = restTemplate.getForObject(url, SearchResponseImdb.class);

        assert responseDtoImdb != null;
        return responseDtoImdb.getResults();
    }
}