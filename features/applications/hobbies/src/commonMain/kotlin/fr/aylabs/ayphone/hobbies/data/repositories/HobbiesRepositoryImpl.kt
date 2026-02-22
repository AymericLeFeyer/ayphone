package fr.aylabs.ayphone.hobbies.data.repositories

import com.woowla.compose.icon.collections.remix.Remix
import com.woowla.compose.icon.collections.remix.remix.Buildings
import com.woowla.compose.icon.collections.remix.remix.Development
import com.woowla.compose.icon.collections.remix.remix.Device
import com.woowla.compose.icon.collections.remix.remix.Finance
import com.woowla.compose.icon.collections.remix.remix.GameAndSports
import com.woowla.compose.icon.collections.remix.remix.Logos
import com.woowla.compose.icon.collections.remix.remix.Map
import com.woowla.compose.icon.collections.remix.remix.Media
import com.woowla.compose.icon.collections.remix.remix.Others
import com.woowla.compose.icon.collections.remix.remix.System
import com.woowla.compose.icon.collections.remix.remix.UserAndFaces
import com.woowla.compose.icon.collections.remix.remix.buildings.HomeLine
import com.woowla.compose.icon.collections.remix.remix.development.CodeSSlashLine
import com.woowla.compose.icon.collections.remix.remix.device.ComputerLine
import com.woowla.compose.icon.collections.remix.remix.device.CpuLine
import com.woowla.compose.icon.collections.remix.remix.device.GamepadLine
import com.woowla.compose.icon.collections.remix.remix.device.ServerLine
import com.woowla.compose.icon.collections.remix.remix.finance.DiamondLine
import com.woowla.compose.icon.collections.remix.remix.gameandsports.Dice5Line
import com.woowla.compose.icon.collections.remix.remix.gameandsports.TargetLine
import com.woowla.compose.icon.collections.remix.remix.logos.GithubLine
import com.woowla.compose.icon.collections.remix.remix.logos.PlaystationLine
import com.woowla.compose.icon.collections.remix.remix.map.RocketLine
import com.woowla.compose.icon.collections.remix.remix.media.FilmLine
import com.woowla.compose.icon.collections.remix.remix.media.VideoLine
import com.woowla.compose.icon.collections.remix.remix.others.FlowerLine
import com.woowla.compose.icon.collections.remix.remix.others.SwordLine
import com.woowla.compose.icon.collections.remix.remix.system.EyeLine
import com.woowla.compose.icon.collections.remix.remix.userandfaces.SpyLine
import com.woowla.compose.icon.collections.remix.remix.userandfaces.UserLine
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesAction
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesLink
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesSection
import fr.aylabs.ayphone.hobbies.domain.models.HomelabTool
import fr.aylabs.ayphone.hobbies.domain.repositories.HobbiesRepository

class HobbiesRepositoryImpl : HobbiesRepository {

    override fun getSections(): List<HobbiesSection> = listOf(
        HobbiesSection(
            title = "PC / Steam",
            icon = Remix.Device.ComputerLine,
            description = "Ma plateforme principale depuis des années. Steam regorge de pépites indépendantes, et j'y ai platinisé mes premiers jeux.\n\nMention spéciale à Dofus — pas sur Steam, mais j'y ai passé tellement d'heures que je ne peux pas ne pas le citer.",
            tools = listOf(
                HomelabTool(
                    name = "Cuphead",
                    description = "Run & gun en animation années 30. Difficile mais magnifique.",
                    logo = Remix.GameAndSports.TargetLine,
                    action = HobbiesAction.OpenUrl("https://store.steampowered.com/app/268910/Cuphead/"),
                ),
                HomelabTool(
                    name = "The Binding of Isaac",
                    description = "Roguelite sombre et addictif. Une run de plus, toujours.",
                    logo = Remix.Others.SwordLine,
                    action = HobbiesAction.OpenUrl("https://store.steampowered.com/app/250900/The_Binding_of_Isaac_Rebirth/"),
                ),
                HomelabTool(
                    name = "Stardew Valley",
                    description = "Jeu de ferme et de vie, parfait pour se détendre.",
                    logo = Remix.Others.FlowerLine,
                    action = HobbiesAction.OpenUrl("https://store.steampowered.com/app/413150/Stardew_Valley/"),
                ),
            ),
        ),
        HobbiesSection(
            title = "Nintendo",
            icon = Remix.Device.GamepadLine,
            description = "Nintendo est une grande partie de mon éducation gaming. J'ai commencé avec la Game Boy Advance SP, enchaîné avec toute la collection Nintendo DS (de la DS à la 3DS XL), la Wii et bien sûr la Switch — incontournable.\n\nMes licences préférées :",
            tools = listOf(
                HomelabTool(
                    name = "Pokémon",
                    description = "Ma première licence. Des centaines d'heures à attraper, dresser, échanger.",
                    logo = Remix.Device.GamepadLine,
                    action = HobbiesAction.OpenUrl("https://www.pokemon.com/fr/"),
                ),
                HomelabTool(
                    name = "Mario Galaxy",
                    description = "La gravité revisitée. L'un des jeux les plus créatifs jamais faits.",
                    logo = Remix.Map.RocketLine,
                    action = HobbiesAction.OpenUrl("https://mario.nintendo.com/"),
                ),
                HomelabTool(
                    name = "Animal Crossing",
                    description = "Le jeu parfait pour souffler. Ma petite île me manque.",
                    logo = Remix.Others.FlowerLine,
                    action = HobbiesAction.OpenUrl("https://www.animal-crossing.com/new-horizons/"),
                ),
            ),
        ),
        HobbiesSection(
            title = "PlayStation",
            icon = Remix.Logos.PlaystationLine,
            description = "Mon père m'a initié au gaming avec Ratchet & Clank sur PS2, quand j'avais 6 ans. Il adorait les consoles PlayStation, alors on a eu toutes les générations de la PS1 à la PS4, en passant par la PSP et la PS Vita.\n\nAujourd'hui j'ai ma propre PS5.",
            tools = listOf(
                HomelabTool(
                    name = "God of War",
                    description = "Épopée nordique entre un père et son fils. Un chef-d'œuvre.",
                    logo = Remix.Others.SwordLine,
                    action = HobbiesAction.OpenUrl("https://store.steampowered.com/app/1593500/God_of_War/"),
                ),
                HomelabTool(
                    name = "Marvel's Spider-Man",
                    description = "La meilleure adaptation du super-héros en jeu vidéo.",
                    logo = Remix.UserAndFaces.UserLine,
                    action = HobbiesAction.OpenUrl("https://store.steampowered.com/app/1817070/Marvels_SpiderMan_Remastered/"),
                ),
            ),
            links = listOf(
                HobbiesLink(
                    icon = Remix.Logos.PlaystationLine,
                    label = "Mon profil PSN",
                    action = HobbiesAction.OpenUrl("https://psnprofiles.com/Aypics"),
                ),
            ),
        ),
        HobbiesSection(
            title = "Jeux de société",
            icon = Remix.GameAndSports.Dice5Line,
            description = "Quand je ne joue pas aux jeux vidéo, je joue aux jeux de société (avec de vraies personnes cette fois !). J'ai découvert ce monde en 2019 avec L'Île Interdite.\n\nDepuis, j'ai ma propre collection. Mes jeux du moment :",
            links = listOf(
                HobbiesLink(
                    icon = Remix.Others.SwordLine,
                    label = "7 Wonders Duel",
                    action = HobbiesAction.OpenUrl("https://boardgamegeek.com/boardgame/173346/7-wonders-duel"),
                ),
                HobbiesLink(
                    icon = Remix.Finance.DiamondLine,
                    label = "Splendor Marvel",
                    action = HobbiesAction.OpenUrl("https://boardgamegeek.com/boardgame/366013/splendor-marvel"),
                ),
                HobbiesLink(
                    icon = Remix.Map.RocketLine,
                    label = "The Crew",
                    action = HobbiesAction.OpenUrl("https://boardgamegeek.com/boardgame/284083/the-crew-the-quest-for-planet-nine"),
                ),
                HobbiesLink(
                    icon = Remix.UserAndFaces.SpyLine,
                    label = "Code Names",
                    action = HobbiesAction.OpenUrl("https://boardgamegeek.com/boardgame/178900/codenames"),
                ),
                HobbiesLink(
                    icon = Remix.Others.FlowerLine,
                    label = "Hanabi",
                    action = HobbiesAction.OpenUrl("https://boardgamegeek.com/boardgame/98778/hanabi"),
                ),
                HobbiesLink(
                    icon = Remix.GameAndSports.Dice5Line,
                    label = "Ma collection MyLudo",
                    action = HobbiesAction.OpenUrl("https://www.myludo.fr/#!/profil/aymeric-23696"),
                ),
            ),
        ),
        HobbiesSection(
            title = "Cinéma",
            icon = Remix.Media.FilmLine,
            description = "J'adore regarder des films, surtout ceux dont on attend beaucoup. Je préfère les regarder avec des amis, parce qu'après la séance on peut passer des heures à en parler — ça ouvre des débats et c'est vraiment sympa.\n\nMa saga préférée, c'est Marvel. Ils ont réussi à construire plus de vingt films (et maintenant des séries) dans le même univers, avec des twists et des liens avec le passé à chaque fois. Un exploit narratif unique.\n\nJ'aime beaucoup Harry Potter aussi. Je me considère presque comme un vrai potterhead, Serdaigle RPZ.",
        ),
        HobbiesSection(
            title = "Développement",
            icon = Remix.Development.CodeSSlashLine,
            description = "Le développement n'est pas qu'un métier, c'est une passion. J'aime explorer de nouvelles technologies et construire des outils qui me correspondent.\n\nAyPhone — l'app que vous êtes en train de parcourir — en est l'exemple parfait : un projet perso en Kotlin Multiplatform, juste pour le plaisir de construire quelque chose.\n\nJ'utilise principalement Kotlin et Compose, avec parfois du Rust ou du Python pour des scripts et des outils en ligne de commande.",
            links = listOf(
                HobbiesLink(
                    icon = Remix.Logos.GithubLine,
                    label = "GitHub",
                    action = HobbiesAction.OpenUrl("https://github.com/AymericLeFeyer"),
                ),
            ),
        ),
        HobbiesSection(
            title = "Domotique",
            icon = Remix.Buildings.HomeLine,
            description = "La domotique, c'est transformer un logement en un espace intelligent — et Home Assistant est au cœur de tout ça.\n\nPlus de 200 entités, des automatisations qui s'adaptent à la vie quotidienne, une philosophie local-first pour garder le contrôle sur ses données. Lumières, capteurs, volets, appareils connectés : tout est centralisé et orchestré depuis un seul endroit, sans dépendre du cloud d'un constructeur.\n\nSi la domotique vous intéresse, j'en parle régulièrement sur AyLabs.",
            links = listOf(
                HobbiesLink(
                    icon = Remix.Buildings.HomeLine,
                    label = "Home Assistant",
                    action = HobbiesAction.OpenUrl("https://www.home-assistant.io/"),
                ),
                HobbiesLink(
                    icon = Remix.Media.FilmLine,
                    label = "Voir AyLabs",
                    action = HobbiesAction.OpenAyLabs,
                ),
            ),
        ),
        HobbiesSection(
            title = "Homelab",
            icon = Remix.Device.ServerLine,
            description = "Le homelab, c'est l'art d'héberger soi-même ses services. Un NAS, quelques machines, et une bonne dose de curiosité suffisent pour reprendre le contrôle sur ses données.\n\nJe gère chez moi une infrastructure complète : vidéo-surveillance avec IA, sauvegarde photos, serveur multimédia, et bien plus encore. J'en parle en détail sur AyLabs.",
            tools = listOf(
                HomelabTool(
                    name = "Frigate",
                    description = "NVR open-source avec détection d'objets par IA en temps réel.",
                    logo = Remix.System.EyeLine,
                    action = HobbiesAction.OpenUrl("https://frigate.video"),
                ),
                HomelabTool(
                    name = "Immich",
                    description = "Solution de sauvegarde et de gestion de photos auto-hébergée.",
                    logo = Remix.Media.VideoLine,
                    action = HobbiesAction.OpenUrl("https://immich.app"),
                ),
                HomelabTool(
                    name = "Plex",
                    description = "Serveur multimédia pour streamer films, séries et musique.",
                    logo = Remix.Media.FilmLine,
                    action = HobbiesAction.OpenUrl("https://www.plex.tv"),
                ),
            ),
            links = listOf(
                HobbiesLink(
                    icon = Remix.Media.FilmLine,
                    label = "Voir AyLabs",
                    action = HobbiesAction.OpenAyLabs,
                ),
            ),
        ),
        HobbiesSection(
            title = "Impression 3D",
            icon = Remix.Device.CpuLine,
            description = "L'impression 3D, c'est donner vie à des idées en quelques heures. Avec une imprimante FDM Bambu Lab et Fusion 360 pour la conception, je crée principalement des pièces utilitaires : supports, accessoires pour la maison et le homelab.\n\nC'est une extension naturelle de l'envie de tout faire soi-même. Je partage parfois mes modèles sur AyLabs, ainsi que sur MakerWorld.",
            links = listOf(
                HobbiesLink(
                    icon = Remix.Device.CpuLine,
                    label = "MakerWorld",
                    action = HobbiesAction.OpenUrl("https://makerworld.com/fr/@aylabs"),
                ),
                HobbiesLink(
                    icon = Remix.Media.FilmLine,
                    label = "Voir AyLabs",
                    action = HobbiesAction.OpenAyLabs,
                ),
            ),
        ),
    )
}
