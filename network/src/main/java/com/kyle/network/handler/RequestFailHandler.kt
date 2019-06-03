package com.kyle.network.handler

/**
 * @author : kyle
 * e-mail : 1239878682@qq.com
 * @date : 2019/5/22 15:37
 * 看了我的代码，感动了吗?
 */
 interface RequestFailHandler{
    fun fail(throwable: Throwable)
}
