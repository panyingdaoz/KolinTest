package com.example.myapplication3

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<T>(private val responseType: Type) : CallAdapter<T, LiveData<T>> {
    override fun adapt(call: Call<T>): LiveData<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return object : LiveData<T>() {
            private val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                // 确保执行一次
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<T> {
                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            postValue(response.body())
                        }

                        override fun onFailure(call: Call<T>, t: Throwable) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            val value = ApiResponse<T>(null, -1, t.message ?: "") as T
                            postValue(value)
                        }
                    })
                }
            }
        }
    }

    override fun responseType() = responseType
}