package fr.aylabs.network

import io.ktor.client.HttpClient
import org.koin.dsl.module

val NETWORK_MODULE = module {
    single<HttpClient> { Client().client }
}