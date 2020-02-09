package com.example.swivltestassigment.data.network.mapper

abstract class ApiMapper<T, V> {
    abstract fun fromEntity(t: T): V;
    abstract fun listFromEntity(t: List<T>?): List<V>;
}