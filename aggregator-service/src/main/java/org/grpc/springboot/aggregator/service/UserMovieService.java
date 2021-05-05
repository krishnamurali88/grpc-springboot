package org.grpc.springboot.aggregator.service;

import org.grpc.springboot.aggregator.dto.RecommendedMovie;
import org.grpc.springboot.aggregator.dto.UserGenre;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.grpc.springboot.common.modules.Genre;
import org.grpc.springboot.movie.modules.MovieSearchRequest;
import org.grpc.springboot.movie.modules.MovieSearchResponse;
import org.grpc.springboot.movie.modules.MovieServiceGrpc;
import org.grpc.springboot.user.modules.UserGenreUpdateRequest;
import org.grpc.springboot.user.modules.UserResponse;
import org.grpc.springboot.user.modules.UserSearchRequest;
import org.grpc.springboot.user.modules.UserServiceGrpc;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMovieService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;

    public List<RecommendedMovie> getUserMovieSuggestions(String loginId) {
        UserSearchRequest userSearchRequest = UserSearchRequest.newBuilder().setLoginId(loginId).build();
        UserResponse userResponse = this.userStub.getUserGenre(userSearchRequest);
        MovieSearchRequest movieSearchRequest = MovieSearchRequest.newBuilder().setGenre(userResponse.getGenre()).build();
        MovieSearchResponse movieSearchResponse = this.movieStub.getMovies(movieSearchRequest);
        return movieSearchResponse.getMovieList()
                .stream()
                .map(movieDto -> new RecommendedMovie(movieDto.getTitle(), movieDto.getYear(), movieDto.getRating()))
                .collect(Collectors.toList());
    }

    public void setUserGenre(UserGenre userGenre) {
        UserGenreUpdateRequest userGenreUpdateRequest = UserGenreUpdateRequest.newBuilder()
                .setLoginId(userGenre.getLoginId())
                .setGenre(Genre.valueOf(userGenre.getGenre().toUpperCase()))
                .build();
        UserResponse userResponse = this.userStub.updateUserGenre(userGenreUpdateRequest);
    }


}
