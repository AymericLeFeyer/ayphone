package fr.aylabs.ayphone.resume.domain.models

data class Company(
    val label: String,
    val iconUrl: String,
) {
    companion object {
        private val registry: MutableMap<String, Company> = mutableMapOf()

        internal fun register(companies: List<Company>) {
            registry.clear()
            companies.forEach { registry[it.label.lowercase()] = it }
        }

        fun fromLabel(name: String): Company? = registry[name.lowercase()]
    }
}
