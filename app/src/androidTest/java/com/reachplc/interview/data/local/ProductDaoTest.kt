package com.reachplc.interview.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.reachplc.interview.getOrAwaitValue
import com.reachplc.interview.model.Model
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var productDatabase: ProductDatabase
    private lateinit var productDao: ProductDao

    @Before
    fun setUp() {
        productDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductDatabase::class.java
        ).allowMainThreadQueries().build()

        productDao = productDatabase.getProductDao()
    }

    @After
    fun teardown() {
        productDatabase.close()
    }


    @Test
    fun insertProduct() = runBlockingTest {

        val productItem = listOf(

            Model(
                id = "1",
                name = "Gucci",
                image = "https://i.imgur.com/Uk8dqpo.png",
                description = "Gucci Bag",
                price = 170.0
            ),
            Model(
                id = "2",
                name = "Nike",
                image = "https://i.imgur.com/Uk8dqpo.png",
                description = "Nike Hat",
                price = 11.9
            ),
            Model(
                id = "3",
                name = "Armani",
                image = "https://i.imgur.com/Uk8dqpo.png",
                description = "Armani T-Shirt",
                price = 45.0
            )

        )

        productDao.insert(productItem)


        val allProducts = productDao.getProducts().asLiveData().getOrAwaitValue()

        assertThat(allProducts)

    }




    @Test
    fun deleteAllProducts() = runBlockingTest {



        val productItem = listOf(

            Model(
                id = "1",
                name = "Gucci",
                image = "https://i.imgur.com/Uk8dqpo.png",
                description = "Gucci Bag",
                price = 170.0
            ),
            Model(
                id = "2",
                name = "Nike",
                image = "https://i.imgur.com/Uk8dqpo.png",
                description = "Nike Hat",
                price = 11.9
            ),
            Model(
                id = "3",
                name = "Armani",
                image = "https://i.imgur.com/Uk8dqpo.png",
                description = "Armani T-Shirt",
                price = 45.0
            )

        )


        productDao.insert(productItem)
        productDao.deleteAll()

        val allshoppingitem = productDao.getProducts().asLiveData().getOrAwaitValue()


        assertThat(allshoppingitem).doesNotContain(productItem)

    }














}


