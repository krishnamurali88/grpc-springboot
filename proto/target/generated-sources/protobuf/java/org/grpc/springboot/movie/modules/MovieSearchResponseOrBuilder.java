// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: movie-service.proto

package org.grpc.springboot.movie.modules;

public interface MovieSearchResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:MovieSearchResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .MovieDto movie = 1;</code>
   */
  java.util.List<org.grpc.springboot.movie.modules.MovieDto> 
      getMovieList();
  /**
   * <code>repeated .MovieDto movie = 1;</code>
   */
  org.grpc.springboot.movie.modules.MovieDto getMovie(int index);
  /**
   * <code>repeated .MovieDto movie = 1;</code>
   */
  int getMovieCount();
  /**
   * <code>repeated .MovieDto movie = 1;</code>
   */
  java.util.List<? extends org.grpc.springboot.movie.modules.MovieDtoOrBuilder> 
      getMovieOrBuilderList();
  /**
   * <code>repeated .MovieDto movie = 1;</code>
   */
  org.grpc.springboot.movie.modules.MovieDtoOrBuilder getMovieOrBuilder(
      int index);
}
