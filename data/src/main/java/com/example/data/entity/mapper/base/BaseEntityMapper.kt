package com.example.data.entity.mapper.base

abstract class BaseEntityMapper<TEntity, TModel> {

    abstract fun transformToEntity(entity: TModel?): TEntity
    abstract fun transformToModel(entity: TEntity?): TModel

    fun transformToEntityCollection(entities: Collection<TModel>): Collection<TEntity> {

        val domainModels: MutableCollection<TEntity> = ArrayList(entities.size)

        for (entity in entities) {
            val domainModel = transformToEntity(entity)
            domainModels.add(domainModel)
        }

        return domainModels
    }

    fun transformToModelCollection(entities: Collection<TEntity>): Collection<TModel> {

        val domainModels: MutableCollection<TModel> = ArrayList(entities.size)

        for (entity in entities) {
            val domainModel = transformToModel(entity)
            domainModels.add(domainModel)
        }

        return domainModels
    }
}
