package com.example.mymovies.data.repository

import com.example.mymovies.data.remoteapi.models.MoviesDiscoveryResult
import com.example.mymovies.data.remoteapi.services.MoviesDiscoveryApiService
import com.example.mymovies.ui.models.MoviesDiscoveryFilters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MoviesDiscoveryRepository {

	suspend fun discoverMoviesByYear(moviesDiscoveryFilters: MoviesDiscoveryFilters): MoviesDiscoveryResult
}

class MoviesDiscoveryRepositoryImpl(private val moviesDiscoveryApiService: MoviesDiscoveryApiService) :
	MoviesDiscoveryRepository {
	override suspend fun discoverMoviesByYear(moviesDiscoveryFilters: MoviesDiscoveryFilters): MoviesDiscoveryResult =
		withContext(Dispatchers.IO) {
			moviesDiscoveryApiService.discoverMoviesByYear(
				moviesDiscoveryFilters.releaseYear,
				moviesDiscoveryFilters.region,
				moviesDiscoveryFilters.language,
				moviesDiscoveryFilters.sortBy,
				moviesDiscoveryFilters.nextMoviesPageToFetch
			)
		}
}
