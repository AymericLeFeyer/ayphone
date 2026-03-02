<div align="center">

# 📱 AyPhone
**https://aymeric.lefeyer.fr**  
**An interactive portfolio that looks and feels like a smartphone.**

Built with Kotlin Multiplatform · Runs on Android & Web (WasmJS)

[![License](https://img.shields.io/badge/license-Custom%20NC-blue.svg)](LICENSE)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.10-7F52FF.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.10.1-4285F4.svg)](https://www.jetbrains.com/lavaflow/compose-multiplatform/)
[![Deployed on Firebase](https://img.shields.io/badge/Deployed%20on-Firebase-FFCA28.svg?logo=firebase)](https://firebase.google.com)

</div>

---

## What is AyPhone?

AyPhone is a personal portfolio built by **Aymeric Le Feyer** — disguised as an operating system. Instead of a traditional webpage, visitors land on a home screen filled with app icons. Each icon opens an interactive "app" that reveals a different aspect of his career: missions, technical skills, clients, side projects, hobbies, and more.

The project targets both **Android** (native APK) and **Web** (WebAssembly via WasmJS), sharing nearly all code through Kotlin Multiplatform and Compose Multiplatform.

---

## Screenshots

<table>
    <tr>
    <td><img src="https://github.com/user-attachments/assets/200c9c53-ad2c-4e31-805a-c90550a685ab" alt="Screen 6" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/e5f52d5b-1dc5-4532-a57a-425656a3ed47" alt="Screen 5" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/11ed4651-b3f4-42c5-b80a-a7ec7d4f9216" alt="Screen 4" width="100%"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/74ef9454-2c0c-4931-8d20-e44fae9f635f" alt="Screen 3" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/8cfca121-dd50-4ab1-b71e-97dd58f69a34" alt="Screen 2" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/6d03484f-8d61-4953-9542-abc0a6edfb6d" alt="Screen 1" width="100%"></td>
  </tr>
  
</table>

## Features

| App | Description |
|---|---|
| **Missions** | Career projects with search, filtering by skill/company, and full detail view |
| **Stack** | Technical skills with proficiency scores and related missions |
| **About** | Bio and contact information |
| **Clients** | Companies worked with, their missions and tech stacks |
| **AyLabs** | YouTube channel stats and latest videos |
| **Side Projects** | Personal projects outside of professional missions |
| **Hobbies** | Gaming, board games, homelab and other interests |
| **AyShop** | Install/uninstall optional apps (Travel, Hobbies, Side Projects) |
| **Timeline** | Career timeline (external link) |
| **Travel** | Travel map (external link) |
| **Settings** | Theme (light/dark/system) and display preferences |
| **Spotlight Search** | Global search across all apps and content |

---

## Backend API

All resume data (missions, companies, skills, contacts, education) is fetched from a dedicated REST API:

**[portfolio-manager](https://github.com/AymericLeFeyer/portfolio-manager/)** — the backend powering AyPhone.

The API exposes:

| Endpoint | Description |
|---|---|
| `GET /api/profile` | Full resume: bio, missions, skills, education, contacts |
| `GET /api/companies` | Company logos and metadata |
| `GET /api/technologies` | Technology icons and metadata |

Data is cached locally for **15 minutes** (via `multiplatform-settings`) to avoid redundant network calls. On cache miss, the app fetches fresh data and repopulates all internal registries.

---

## Architecture

AyPhone follows **Clean Architecture** throughout, structured around the principle that the data provider (`resume` module) has no knowledge of its consumers (Missions, Stack, Clients, etc.).

### Module Graph

```
composeApp  ─── Entry point (Android + Web), DI init, root navigation
│
├── core/
│   ├── network          Ktor HttpClient (content negotiation, logging, platform engines)
│   ├── design-system    AyColors, AySpacings, shared scaffolds, icon/UI re-exports
│   ├── settings         Key-value storage (multiplatform-settings, clean arch)
│   └── dates            kotlinx-datetime utilities
│
└── features/
    ├── application      AyApp enum — all app metadata (title, icon, color, description)
    ├── frame            Home screen grid (4-column LazyVerticalGrid, Spotlight overlay)
    ├── widget           PhotoWidget + TextWidget (header of the home screen)
    ├── search           Spotlight search (cross-module, global)
    │
    └── applications/
        ├── resume       ★ Data hub — full clean arch, no UI (DTOs, mappers, repos, use cases)
        ├── missions     Career projects list + detail + filtering
        ├── stack        Tech skills + proficiency + related missions
        ├── about        Bio and contact screen
        ├── clients      Companies + missions per client
        ├── aylabs       YouTube channel info + stats
        ├── sideprojects Personal projects
        ├── hobbies      Gaming, board games, homelab
        ├── ayshop       App install/uninstall store
        ├── settings     Theme and display preferences
        ├── timeline     External link wrapper
        └── travel       External link wrapper
```

### Clean Architecture Layers

The `resume` module is the single source of truth for all professional data. It implements the full clean architecture stack:

```
data/
  ├── datasources/    Remote (Ktor) + Local (settings cache, 15-min TTL)
  ├── dtos/           @Serializable JSON contracts (ResumeDto, MissionDto, ...)
  ├── mappers/        DTO → Domain model transformations
  └── repositories/   ResumeRepositoryImpl (manages caching + registry population)

domain/
  ├── models/         Pure Kotlin data classes (Resume, ResumeMission, Skill, Company, ...)
  ├── repositories/   ResumeRepository interface
  └── usecases/       GetResumeUseCase, GetDarkModeUseCase, SetDarkModeUseCase
```

Consumer modules (Missions, Stack, Clients...) only depend on the domain layer — they inject and call `GetResumeUseCase` and never touch network or DTO code directly.

### Registry Pattern

`Skill` and `Company` are domain models that carry icon URLs fetched from the API. Because they’re referenced by ID across many models, they use a **static registry** populated once at startup:

```kotlin
// Skill.register(id, label, iconUrl) populates a companion object map
// Company.register(id, label, iconUrl) does the same
// All downstream models resolve icon/label by ID from the registry
```

This avoids passing icon data through every DTO and keeps domain models lean.

### State Management

Each feature ViewModel exposes a `StateFlow` of a sealed class:

```kotlin
sealed class MissionsState {
    data object Initial : MissionsState()
    data object Loading : MissionsState()
    data class Success(val missions: List<ResumeMission>, ...) : MissionsState()
    data class Error(val message: String) : MissionsState()
}
```

### Navigation

Navigation uses **Compose Navigation with type-safe routes** (`@Serializable` data objects, `@SerialName` for stable serialization). Each feature module exposes a `NavGraphBuilder` extension function:

```kotlin
fun NavGraphBuilder.missionsGraph(
    onNavigateToSkill: (String) -> Unit,
    onNavigateToClient: (String) -> Unit,
)
```

Cross-module navigation is handled via **callback lambdas** wired in `MainNavHost` — modules never reference each other’s routes directly, keeping the dependency graph clean.

**Transitions:**
- App open: `scaleIn(0.88f) + fadeIn` (350ms)
- App close: `scaleOut(0.96f) + fadeOut` (280ms)
- Internal navigation: `fadeIn/Out` (220ms)

### Dependency Injection

Koin is used throughout. Each module declares its own `val MODULE = module { ... }`. All modules are aggregated in `composeApp`:

```kotlin
val SHARED_MODULES = listOf(
    NETWORK_MODULE, RESUME_MODULE,
    MISSIONS_MODULE, STACK_MODULE, ABOUT_MODULE, CLIENTS_MODULE,
    AYLABS_MODULE, SIDE_PROJECTS_MODULE, HOBBIES_MODULE,
    AYSHOP_MODULE, SETTINGS_MODULE, SETTINGS_APP_MODULE,
    FRAME_MODULE, SEARCH_MODULE,
    createSettingsModule(),  // platform-specific settings factory
)
```

Android initializes Koin with `androidContext` and `androidLogger`; the Web target uses a bare `initKoin()`.

### Platform-Specific Code

`expect`/`actual` declarations handle platform divergence cleanly:

| Declaration | Android | WasmJS |
|---|---|---|
| `IsDebug` | `BuildConfig.DEBUG` | `false` |
| Settings Koin module | `SharedPreferencesSettings` | `localStorage`-backed |
| HTTP engine | `OkHttp` | `Js` |

---

## Tech Stack

| Library | Version | Role |
|---|---|---|
| Kotlin | 2.3.10 | Language |
| Compose Multiplatform | 1.10.1 | UI framework |
| Koin | 4.1.1 | Dependency injection |
| Ktor | 3.4.0 | HTTP client |
| kotlinx.serialization | (bundled) | JSON parsing |
| kotlinx-coroutines | 1.10.2 | Async & concurrency |
| kotlinx-datetime | 0.7.1 | Date/time utilities |
| multiplatform-settings | 1.3.0 | Key-value persistence |
| Coil | 3.3.0 | Image loading (remote icons) |
| Kermit | 2.0.8 | Multiplatform logging |
| Woowla Remix Icons | 4.9.0 | Icon library |
| Navigation Compose | 2.9.2 | Type-safe routing |
| Material3 | 1.10.0-alpha05 | Design system base |

---

## Project Structure

```
ayphone/
├── composeApp/
│   └── src/
│       ├── commonMain/        App.kt, MainNavHost.kt, di/
│       ├── androidMain/       MainActivity.kt
│       └── webMain/
│           ├── kotlin/        main.kt
│           ├── resources/     index.html, styles.css
│           └── docker/        Dockerfile, nginx.conf
├── core/
│   ├── network/
│   ├── design-system/
│   ├── settings/
│   └── dates/
├── features/
│   ├── application/
│   ├── frame/
│   ├── widget/
│   ├── search/
│   └── applications/
│       ├── resume/            (no UI — data hub only)
│       ├── missions/
│       ├── stack/
│       ├── about/
│       ├── clients/
│       ├── aylabs/
│       ├── sideprojects/
│       ├── hobbies/
│       ├── ayshop/
│       ├── settings/
│       ├── timeline/
│       └── travel/
└── gradle/
    └── libs.versions.toml
```

---

## Build & Run

### Prerequisites

- JDK 17+
- Android Studio (for Android target)
- A browser supporting WebAssembly (for Web target)

### Commands

```shell
# Android debug build
./gradlew :composeApp:assembleDebug

# Web — development server with hot reload
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Web — production bundle
./gradlew :composeApp:wasmJsBrowserProductionWebpack

# Run tests for a module
./gradlew :features:applications:resume:allTests
./gradlew :core:network:allTests

# Full project build
./gradlew build
```

### Web Deployment (Docker + nginx)

The web build is served by nginx and deployed to Firebase Hosting via GitHub Actions.

```shell
# Build the production bundle, then:
docker build -f composeApp/src/webMain/docker/Dockerfile -t ayphone .
docker run -p 80:80 ayphone
```

The nginx configuration handles:
- Correct MIME types for `.mjs` and `.wasm` files (required by Skiko)
- Cache headers: no-cache for HTML, etag-based revalidation for JS/Wasm/CSS
- SPA fallback to `/index.html`
- Gzip compression for JS and Wasm

---

## CI/CD

GitHub Actions workflow (`.github/workflows/docker.yml`) runs on every push to `main`:

1. Setup Java 17 (Temurin)
2. Build WasmJS production bundle
3. Assemble the `public/` directory (processed resources + webpack output)
4. Authenticate with Google (Firebase service account)
5. Deploy to **Firebase Hosting**

---

## Adding a New App

1. Create a module under `features/applications/my-app/`
2. Add the app entry to the `AyApp` enum in `features/application/` (title, icon, color, description)
3. Implement your UI: screens, ViewModel, state, navigation graph
4. Create a Koin module and register it in `SHARED_MODULES` (`composeApp/di/KoinModule.kt`)
5. Wire the navigation graph in `MainNavHost.kt`

---

## License

This project is open source. You are free to read, fork, and learn from it.
**Commercial resale or redistribution for profit is strictly prohibited.**

See [LICENSE](LICENSE) for full terms.

---

<div align="center">

Made with ❤️ by [Aymeric Le Feyer](https://github.com/AymericLeFeyer)

</div>
