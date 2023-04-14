package com.example.mymovies.domain.models

import com.example.mymovies.data.remoteapi.ApiUrlsManager
import com.example.mymovies.data.remoteapi.models.MoviesDiscoveryResult

data class MovieMainDetails(
	val originalTitle: String,
	val translatedTitle: String,
	val posterUrl: String?
)

fun MoviesDiscoveryResult.asDomainModel(): List<MovieMainDetails> = movies.map { movie ->
	MovieMainDetails(
		movie.originalTitle,
		movie.translatedTitle,
		movie.posterPath?.let { posterPath ->
			ApiUrlsManager.ApiImageUtils.buildFullUrlImage(
				posterPath,
				ApiUrlsManager.ApiImageUtils.PosterMovieSize.WIDTH_500PX
			)
		}
	)
}