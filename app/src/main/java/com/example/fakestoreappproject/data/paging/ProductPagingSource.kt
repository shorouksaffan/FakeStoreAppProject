package com.example.fakestoreappproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fakestoreappproject.data.model.Product
import com.example.fakestoreappproject.data.network.ApiResult
import com.example.fakestoreappproject.data.network.ApiService
import com.example.fakestoreappproject.data.repository.ProductRepository

class ProductPagingSource(
    private val repository: ProductRepository,
    private val limit: Int
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(limit)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(limit)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val offset = params.key ?: 0
        return when (val result = repository.getProducts(offset, limit)) {
            is ApiResult.Success -> {
                LoadResult.Page(
                    data = result.data,
                    prevKey = if (offset == 0) null else offset - limit,
                    nextKey = if (result.data.isEmpty()) null else offset + limit
                )
            }
            is ApiResult.Error -> LoadResult.Error(result.exception)
        }
    }
}