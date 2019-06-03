package com.kyle.network.factory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author : kyle
 * e-mail : 1239878682@qq.com
 * @date   : 2019/4/23 18:06
 * 看了我的代码，感动了吗?
 */
class CommonConverterFactory private constructor(private val gson: Gson) : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?,
                                       retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type!!))
        return CommonResponseConverter(gson, adapter)
    }

    override fun requestBodyConverter(type: Type?,
                                      parameterAnnotations: Array<Annotation>?, methodAnnotations: Array<Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        val adapter = gson.getAdapter(TypeToken.get(type!!))
        return CommonRequestConverter(gson, adapter)
    }

    companion object {

        fun create(): CommonConverterFactory {
            return create(Gson())
        }

        /***
         *
         * @param gson
         * @return
         */
        fun create(gson: Gson?): CommonConverterFactory {
            if (gson == null) {
                throw NullPointerException("gson == null")
            }
            return CommonConverterFactory(gson)
        }
    }
}
