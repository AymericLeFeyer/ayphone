package fr.aylabs.ayphone.resume.domain.models

data class Skill(
    val label: String,
    val iconUrl: String,
    val category: String,
    val description: String = "",
) {
    companion object {
        private val registry: MutableMap<String, Skill> = mutableMapOf()

        internal fun register(skills: List<Skill>) {
            registry.clear()
            skills.forEach { registry[it.label.lowercase()] = it }
        }

        fun fromLabel(name: String): Skill? = registry[name.lowercase()]
    }
}
