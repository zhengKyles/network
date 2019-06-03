package com.kyle.network

import android.content.Context
import com.kyle.network.handler.RequestHandler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @author : kyle
 * e-mail : 1239878682@qq.com
 * @date : 2019/4/24 11:07
 * 看了我的代码，感动了吗?
 */
class Request private constructor() {
    private var context: Context? = null
    private val compositeDisposableMap = HashMap<Context, CompositeDisposable>()

    companion object {
        private val INSTANCE: Request by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Request()
        }

        fun getInstance(context: Context): Request {
            var instance = INSTANCE
            if (instance.compositeDisposableMap[context] == null) {
                instance.compositeDisposableMap[context] = CompositeDisposable()
            }
            return instance
        }
    }

    fun <T> request(observable: Observable<T>, handler: RequestHandler<T>) {
        val disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> handler.success(t) }, { throwable -> handler.fail(throwable) }, { handler.complete() })
        compositeDisposableMap[context]!!.add(disposable)
    }

    fun disposeAll() {
        compositeDisposableMap[context].let {
            it?.dispose()
        }
    }

}
