package com.kyle.network.factory

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

/**
 * @author : kyle
 * e-mail : 1239878682@qq.com
 * @date   : 2019/4/23 18:03
 * 看了我的代码，感动了吗?
 */
class CommonResponseConverter<T> internal constructor(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val jsonReader = gson.newJsonReader(value.charStream())
        value.use {
            return adapter.read(jsonReader)
        }
    }
}
