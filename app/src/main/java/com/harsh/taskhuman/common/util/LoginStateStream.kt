package com.harsh.taskhuman.common.util

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LoginStateStream {
    private val actualSubject = PublishSubject.create<Boolean>()
    private val serializedSubject = actualSubject.toSerialized()

    private fun post(loginState: Boolean) = serializedSubject.onNext(loginState)

    fun stream(): Observable<Boolean> = actualSubject.hide()

    fun logOut() = post(false)

    fun logIn() = post(true)
}
