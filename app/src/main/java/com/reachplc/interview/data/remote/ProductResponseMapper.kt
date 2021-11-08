package com.reachplc.interview.data.remote

import com.reachplc.interview.model.Model
import com.reachplc.interview.utils.ModelMapper

class ProductResponseMapper : ModelMapper<ProductsResponse.Product, Model> {

    override fun toModel(entity: ProductsResponse.Product): Model {
        return Model(
            id = entity.id,
            name = entity.name,
            image = entity.image,
            description = entity.description,
            price = entity.price
        )
    }

    override fun fromModel(model: Model): ProductsResponse.Product {
        TODO("Not yet implemented")
    }

    fun toModelList(entity: List<ProductsResponse.Product>): List<Model> {
        return entity.map { toModel(it) }
    }

}