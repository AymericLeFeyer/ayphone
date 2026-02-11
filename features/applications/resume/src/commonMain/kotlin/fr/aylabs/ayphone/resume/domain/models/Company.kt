package fr.aylabs.ayphone.resume.domain.models

import ayphone.features.applications.resume.generated.resources.Res
import ayphone.features.applications.resume.generated.resources.aylabs
import ayphone.features.applications.resume.generated.resources.baggou
import ayphone.features.applications.resume.generated.resources.bonap
import ayphone.features.applications.resume.generated.resources.caroline_optique
import ayphone.features.applications.resume.generated.resources.conhexa
import ayphone.features.applications.resume.generated.resources.decathlon
import ayphone.features.applications.resume.generated.resources.dunfast
import ayphone.features.applications.resume.generated.resources.habidom
import ayphone.features.applications.resume.generated.resources.lefourgon
import ayphone.features.applications.resume.generated.resources.lesieur
import ayphone.features.applications.resume.generated.resources.lycee
import ayphone.features.applications.resume.generated.resources.neo
import ayphone.features.applications.resume.generated.resources.ocee
import ayphone.features.applications.resume.generated.resources.paragon
import ayphone.features.applications.resume.generated.resources.parkontime
import ayphone.features.applications.resume.generated.resources.pit
import ayphone.features.applications.resume.generated.resources.smartfresh
import ayphone.features.applications.resume.generated.resources.speak
import ayphone.features.applications.resume.generated.resources.talentplug
import ayphone.features.applications.resume.generated.resources.univ_lille
import ayphone.features.applications.resume.generated.resources.uphf
import org.jetbrains.compose.resources.DrawableResource

enum class Company(
    val label: String,
    val icon: DrawableResource,
) {
    AYLABS("AyLabs", Res.drawable.aylabs),
    BAGGOU("Baggou", Res.drawable.baggou),
    BONAP("Bonap", Res.drawable.bonap),
    CAROLINE_OPTIQUE("Caroline Optique", Res.drawable.caroline_optique),
    CONHEXA("Conhexa", Res.drawable.conhexa),
    DECATHLON("Decathlon", Res.drawable.decathlon),
    DUNFAST("Dunfast", Res.drawable.dunfast),
    HABIDOM("Habidom", Res.drawable.habidom),
    LE_FOURGON("Le Fourgon", Res.drawable.lefourgon),
    LESIEUR("Lesieur", Res.drawable.lesieur),
    LYCEE("Lycée", Res.drawable.lycee),
    NEO("Néo", Res.drawable.neo),
    OCEE("Ocee", Res.drawable.ocee),
    PARAGON("Paragon-ID", Res.drawable.paragon),
    PARKONTIME("ParkOnTime", Res.drawable.parkontime),
    PIT("PIT", Res.drawable.pit),
    SMARTFRESH("SmartFresh", Res.drawable.smartfresh),
    SPEAK("Speak", Res.drawable.speak),
    TALENTPLUG("TalentPlug", Res.drawable.talentplug),
    UNIV_LILLE("Université de Lille", Res.drawable.univ_lille),
    UPHF("UPHF", Res.drawable.uphf),
    ;

    companion object {
        fun fromLabel(name: String): Company? =
            entries.find { it.label.equals(name, ignoreCase = true) }
    }
}
