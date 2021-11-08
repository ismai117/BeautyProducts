package com.reachplc.interview.repository

import androidx.room.withTransaction
import com.reachplc.interview.data.local.ProductDao
import com.reachplc.interview.data.local.ProductDatabase
import com.reachplc.interview.data.remote.ProductResponseMapper
import com.reachplc.interview.data.remote.ProductsService
import com.reachplc.interview.model.Model
import com.reachplc.interview.utils.Resource
import com.reachplc.interview.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository
@Inject
constructor(
    private val productsService: ProductsService,
    private val productResponseMapper: ProductResponseMapper,
    private val productDao: ProductDao,
    private val productDatabase: ProductDatabase,
) {


    fun productsRepo(): Flow<Resource<List<Model>>> {

        return networkBoundResource(
            query = {
                productDao.getProducts()
            },
            fetch = {
                productResponseMapper.toModelList(productsService.getProducts().products)
            },
            saveFetchResult = {
                productDatabase.withTransaction {
                    productDao.deleteAll()
                    productDao.insert(it)
                }
            }
        )

    }


    fun getDatabaseQuery(product: String): Flow<List<Model>> {
        return productDao.searchDatabase(product)
    }


}