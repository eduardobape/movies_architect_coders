package com.example.mymovies.domain.models

import com.example.mymovies.data.remoteapi.ApiUrlsManager
import com.example.mymovies.data.remoteapi.models.MoviesDiscoveryResult

data class MoviesDiscoveryDetails(
	val pages: Int,
	val page: Int,
	val movies: List<MovieMainDetails>
)

data class MovieMainDetails(
	val id: Int,
	val originalTitle: String,
	val translatedTitle: String,
	val posterUrl: String?
)

fun MoviesDiscoveryResult.asDomainModel(): MoviesDiscoveryDetails = MoviesDiscoveryDetails(
	totalPages,
	page,
	movies.map { movie ->
		MovieMainDetails(
			movie.id,
			movie.originalTitle,
			movie.translatedTitle,
			movie.posterPath?.let { posterPath ->
				ApiUrlsManager.ApiImageUtils.buildFullUrlImage(
					posterPath,
					ApiUrlsManager.ApiImageUtils.PosterMovieSize.WIDTH_342PX
				)
			}
		)
	}
)