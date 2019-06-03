package com.kyle.network.handler

/**
 * @author : kyle
 * e-mail : 1239878682@qq.com
 * @date : 2019/4/24 11:32
 * 看了我的代码，感动了吗?
 */
interface RequestHandler<T> :RequestFailHandler{
    fun success(t: T)
    fun complete()
}
