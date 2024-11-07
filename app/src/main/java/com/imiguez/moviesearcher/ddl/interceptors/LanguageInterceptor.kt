package com.imiguez.moviesearcher.ddl.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class LanguageInterceptor(private val language: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Obtiene la solicitud original
        val originalRequest = chain.request()

        // Construye la nueva URL con los parámetros de consulta
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("language", language)  // Agregar parámetros aquí
            .build()

        // Crea una nueva solicitud con la URL modificada
        val newRequest = originalRequest.newBuilder()
            .url(url)
            .build()

        // Procede con la solicitud modificada
        return chain.proceed(newRequest)
    }
}