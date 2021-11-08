package com.reachplc.interview.utils

import com.reachplc.interview.model.Model


interface ModelMapper<Entity, Model> {

    fun toModel(entity: Entity): Model

    fun fromModel(model: Model): Entity

}