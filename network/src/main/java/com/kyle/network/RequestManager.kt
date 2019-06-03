package com.kyle.network

import com.kyle.network.factory.CommonConverterFactory
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * @author : kyle
 * e-mail : 1239878682@qq.com
 * @date   : 2019/4/23 18:03
 * 看了我的代码，感动了吗?
 */
class RequestManager private constructor() {

    var retrofit: Retrofit? = null
        private set

    companion object {
        val INSTANCE: RequestManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RequestManager()
        }
        private const val BASE_URL = "http://www.baidu.com"
        private const val TIMEOUT = 10
        private val TIMEUNIT = TimeUnit.SECONDS
    }
    fun initRetrofit() {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT.toLong(), TIMEUNIT)
                .writeTimeout(TIMEOUT.toLong(), TIMEUNIT)
                .readTimeout(TIMEOUT.toLong(), TIMEUNIT)
                .callTimeout(TIMEOUT.toLong(), TIMEUNIT)
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.e(message) }))
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(CommonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

}
