package fr.aylabs.ayphone.resume.domain.models

enum class Company(
    val label: String,
    val iconPath: String,
) {
    AYLABS("AyLabs", "drawable/aylabs.png"),
    BAGGOU("Baggou", "drawable/baggou.png"),
    BONAP("Bonap", "drawable/bonap.png"),
    CAROLINE_OPTIQUE("Caroline Optique", "drawable/caroline-optique.png"),
    CONHEXA("Conhexa", "drawable/conhexa.png"),
    DECATHLON("Decathlon", "drawable/decathlon.png"),
    DUNFAST("Dunfast", "drawable/dunfast.png"),
    HABIDOM("Habidom", "drawable/habidom.png"),
    LE_FOURGON("Le Fourgon", "drawable/lefourgon.png"),
    LESIEUR("Lesieur", "drawable/lesieur.png"),
    LYCEE("Lycée", "drawable/lycee.png"),
    NEO("Néo", "drawable/neo.png"),
    OCEE("Ocee", "drawable/ocee.png"),
    PARAGON("Paragon-ID", "drawable/paragon.png"),
    PARKONTIME("ParkOnTime", "drawable/parkontime.png"),
    PIT("PIT", "drawable/pit.png"),
    SMARTFRESH("SmartFresh", "drawable/smartfresh.png"),
    SPEAK("Speak", "drawable/speak.png"),
    TALENTPLUG("TalentPlug", "drawable/talentplug.jpg"),
    UNIV_LILLE("Université de Lille", "drawable/univ-lille.png"),
    UPHF("UPHF", "drawable/uphf.png"),
    ;

    companion object {
        fun fromLabel(name: String): Company? =
            entries.find { it.label.equals(name, ignoreCase = true) }
    }
}
