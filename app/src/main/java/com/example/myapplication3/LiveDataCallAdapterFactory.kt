package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        // 获取第一个泛型类型
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(observableType)
        if (rawType != ApiResponse::class.java) {
            throw IllegalAccessException("type must be ApiResponse")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalAccessException("resource must be parameterized")
        }
        return LiveDataCallAdapter<Any>(observableType)
    }
}
