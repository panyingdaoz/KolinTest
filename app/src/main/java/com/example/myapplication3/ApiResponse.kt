package com.example.myapplication3

/**
 *  通用的响应实体
 */
class ApiResponse<T>(
    var data: T?,
    var errorCode: Int,
    var errorMsg: String
)

