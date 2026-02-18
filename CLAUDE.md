# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this
repository.

## Build Commands

```shell
# Android debug build
.\gradlew.bat :composeApp:assembleDebug

# Web (WasmJS) development run
.\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun

# Run tests for a specific module
.\gradlew.bat :features:applications:resume:allTests
.\gradlew.bat :core:network:allTests

# Full project build
.\gradlew.bat build
```

## Project Overview

AyPhone is a Kotlin Multiplatform (KMP) app targeting **Android** and **Web (WasmJS)** using Compose
Multiplatform. It simulates a phone home screen where each "app" is a feature module. Current apps:
**Missions**, **Stack**, **About**, and **Clients** — all powered by the **Resume** data module.

Package root: `fr.aylabs.ayphone`

## Architecture

### Module Structure

- **`composeApp`** — Entry point. Contains `App.kt` (root composable with MaterialTheme),
  `MainNavHost.kt` (top-level navigation), DI initialization (`di/KoinInit.kt`), and platform main
  functions.
- **`core/`** — Shared infrastructure modules:
    - `network` — Ktor HttpClient setup with content negotiation and logging
    - `settings` — Key-value storage via `multiplatform-settings` library (clean arch: datasource →
      repository → usecases)
    - `design-system` — Shared colors (`AyColors`), re-exports Material3/Compose/Navigation/Woowla
      icons as `api` dependencies
    - `dates` — Date utilities wrapping `kotlinx-datetime`
- **`features/`** — Feature modules:
    - `application` — Abstract `Application` base class (title, logo, onClick) that all "apps"
      extend
    - `frame` — Home screen grid (`Frame.kt`) displaying `Application` icons via
      `LazyVerticalGrid` (4 columns)
    - `applications/resume` — Data-only module (no UI). Full clean architecture (`data/` →
      `domain/`): DTOs, datasources, mappers, repositories, use cases. Serves as the single source
      of truth for all other apps.
    - `applications/missions` — Career missions/projects with filtering by skills and companies
    - `applications/stack` — Technical skills/stack display
    - `applications/about` — About/bio information
    - `applications/clients` — Companies/clients worked with

### Clean Architecture Pattern

**Resume module** (full clean architecture — data provider, no UI):

- **data**: DTOs (`@Serializable`), datasources (Ktor calls), mappers (DTO→Model), repository
  implementations
- **domain**: Models (data classes), repository interfaces, use cases (e.g., `GetResumeUseCase`)

**App modules** (Missions, Stack, About, Clients — UI consumers):

- **domain**: Only the `Application` subclass (e.g., `MissionsApp`)
- **ui**: Screens, ViewModels, states, navigation graph, components. ViewModels call Resume's use
  cases to get data.

### Key Patterns

- **DI**: Koin. Each module exports a `val MODULE_NAME = module { ... }` (e.g., `RESUME_MODULE`,
  `MISSIONS_MODULE`). All modules aggregated in `composeApp/di/KoinModule.kt` as `SHARED_MODULES` (
  includes: `NETWORK_MODULE`, `RESUME_MODULE`, `MISSIONS_MODULE`, `STACK_MODULE`, `ABOUT_MODULE`,
  `CLIENTS_MODULE`, `SETTINGS_MODULE`, `createSettingsModule()`).
- **Navigation**: Type-safe routes using `@Serializable` data objects with `@SerialName`
  annotations. Each app provides a `NavGraphBuilder` extension (e.g., `missionsGraph()`,
  `stackGraph()`, `aboutGraph()`, `clientsGraph()`). Cross-module navigation via callback lambdas (
  e.g., Missions → Stack/Clients). Transitions use `fadeIn()`/`fadeOut()`.
- **State**: ViewModels use `MutableStateFlow` with sealed class states (`Initial`, `Loading`,
  `Success`, `Error`).
- **Platform-specific code**: Uses `expect`/`actual` declarations (e.g., `ResumeRemoteDatasource`
  URL, settings Koin module). Platform source sets: `androidMain`, `wasmJsMain`.

### Adding a New "App"

1. Create a module under `features/applications/`
2. Extend `Application` abstract class with title, logo, and onClick navigation
3. Create a Koin module and register it in `SHARED_MODULES`
4. Add a navigation graph extension and wire it in `MainNavHost`
5. Add the app instance to the list in `Frame.kt`

## Key Dependencies

| Lib                          | Purpose              |
|------------------------------|----------------------|
| Kotlin 2.3.10               | Language             |
| Compose Multiplatform 1.10.1 | UI framework         |
| Koin 4.1.1                  | Dependency injection |
| Ktor 3.4.0                  | HTTP client          |
| kotlinx.serialization       | JSON serialization   |
| kotlinx-datetime 0.7.1      | Date/time utilities  |
| kotlinx-coroutines 1.10.2   | Async/concurrency    |
| multiplatform-settings 1.3.0 | Key-value persistence |
| Coil 3.3.0                  | Image loading        |
| Kermit 2.0.8                | Multiplatform logging |
| Woowla Remix Icons 4.9.0    | Icon library         |

## Rules

- **Never run tests or compilation commands.** The user will handle builds and tests manually.
- **Bypass mode:** Act autonomously — do not ask for confirmation or clarification. Execute directly. Only pause before truly destructive commands (force-push to main, `rm -rf`, dropping databases, etc.).
