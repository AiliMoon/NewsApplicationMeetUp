package com.example.data.entity.mapper.base

abstract class SimpleEntityMapper<TModel, TEntity> {
    abstract fun transform(entity: TEntity): TModel

    fun transformCollection(entities: Collection<TEntity>): Collection<TModel> {

        val domainModels: MutableCollection<TModel> = ArrayList(entities.size)

        for (entity in entities) {
            val domainModel = transform(entity)
            domainModels.add(domainModel)
        }

        return domainModels
    }
}
