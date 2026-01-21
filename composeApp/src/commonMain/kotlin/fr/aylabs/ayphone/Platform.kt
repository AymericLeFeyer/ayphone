package fr.aylabs.ayphone

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform