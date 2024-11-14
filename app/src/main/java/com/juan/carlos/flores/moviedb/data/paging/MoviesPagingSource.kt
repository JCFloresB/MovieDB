package com.juan.carlos.flores.moviedb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juan.carlos.flores.moviedb.data.mapper.MovieMapper.toDomain
import com.juan.carlos.flores.moviedb.data.remote.api.ApiService
import com.juan.carlos.flores.moviedb.domain.model.Movie
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val api: ApiService,
    private val apiKey: String,
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = api.getPopularMovies(apiKey, page)

            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.totalPages) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}