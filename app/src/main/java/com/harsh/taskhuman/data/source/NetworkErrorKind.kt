package com.harsh.taskhuman.data.source

/**
 * Created by Harsh Mittal on 15/07/22.
 **/
enum class NetworkErrorKind {
    SERVER,
    INTERNET,
    HTTP,
    IO,
    SOCKET_TIMEOUT,
    RESPONSE_PARSING,
    UNKNOWN
}