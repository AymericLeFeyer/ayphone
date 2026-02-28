package fr.aylabs.ayphone.resume.domain.models

enum class Company(
    val label: String,
    val iconUrl: String,
) {
    AYLABS("AyLabs", "https://api.aymeric.lefeyer.fr/icons/companies/aylabs.png"),
    BAGGOU("Baggou", "https://api.aymeric.lefeyer.fr/icons/companies/baggou.png"),
    BONAP("Bonap", "https://api.aymeric.lefeyer.fr/icons/companies/bonap.png"),
    CAROLINE_OPTIQUE("Caroline Optique", "https://api.aymeric.lefeyer.fr/icons/companies/caroline-optique.png"),
    CONHEXA("Conhexa", "https://api.aymeric.lefeyer.fr/icons/companies/conhexa.png"),
    DECATHLON("Decathlon", "https://api.aymeric.lefeyer.fr/icons/companies/decathlon.png"),
    DUNFAST("Dunfast", "https://api.aymeric.lefeyer.fr/icons/companies/dunfresh.png"),
    HABIDOM("Habidom", "https://api.aymeric.lefeyer.fr/icons/companies/habidom.png"),
    LE_FOURGON("Le Fourgon", "https://api.aymeric.lefeyer.fr/icons/companies/lefourgon.png"),
    LESIEUR("Lesieur", "https://api.aymeric.lefeyer.fr/icons/companies/lesieur.png"),
    LYCEE("Lycée", "https://api.aymeric.lefeyer.fr/icons/companies/lycee.png"),
    NEO("Néo", "https://api.aymeric.lefeyer.fr/icons/companies/neo.png"),
    OCEE("Ocee", "https://api.aymeric.lefeyer.fr/icons/companies/ocee.png"),
    PARAGON("Paragon-ID", "https://api.aymeric.lefeyer.fr/icons/companies/paragon.png"),
    PARKONTIME("ParkOnTime", "https://api.aymeric.lefeyer.fr/icons/companies/parkontime.png"),
    PIT("PIT", "https://api.aymeric.lefeyer.fr/icons/companies/pit.png"),
    SMARTFRESH("SmartFresh", "https://api.aymeric.lefeyer.fr/icons/companies/smartfresh.png"),
    SPEAK("Speak", "https://api.aymeric.lefeyer.fr/icons/technologies/speak.png"),
    TALENTPLUG("TalentPlug", "https://api.aymeric.lefeyer.fr/icons/companies/talentplug.jpg"),
    UNIV_LILLE("Université de Lille", "https://api.aymeric.lefeyer.fr/icons/companies/univ-lille.png"),
    UPHF("UPHF", "https://api.aymeric.lefeyer.fr/icons/companies/uphf.png"),
    ;

    companion object {
        fun fromLabel(name: String): Company? =
            entries.find { it.label.equals(name, ignoreCase = true) }
    }
}
