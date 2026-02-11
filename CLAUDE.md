# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

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

AyPhone is a Kotlin Multiplatform (KMP) app targeting **Android** and **Web (WasmJS)** using Compose Multiplatform. It simulates a phone home screen where each "app" is a feature module. The only app currently implemented is **Resume**.

Package root: `fr.aylabs.ayphone`

## Architecture

### Module Structure

- **`composeApp`** — Entry point. Contains `App.kt` (root composable with MaterialTheme), `MainNavHost.kt` (top-level navigation), DI initialization (`di/KoinInit.kt`), and platform main functions.
- **`core/`** — Shared infrastructure modules:
  - `network` — Ktor HttpClient setup with content negotiation and logging
  - `settings` — Key-value storage via `multiplatform-settings` library (clean arch: datasource → repository → usecases)
  - `design-system` — Shared colors (`AyColors`), re-exports Material3/Compose/Navigation/Woowla icons as `api` dependencies
  - `dates` — Date utilities wrapping `kotlinx-datetime`
- **`features/`** — Feature modules:
  - `application` — Abstract `Application` base class (title, logo, onClick) that all "apps" extend
  - `frame` — Home screen grid (`Frame.kt`) displaying `Application` icons via `LazyVerticalGrid`
  - `applications/resume` — Full-featured resume app with its own clean architecture layers

### Clean Architecture Pattern (per feature)

Each feature follows: `data/` → `domain/` → `ui/`

- **data**: DTOs (`@Serializable`), datasources (Ktor calls), mappers (DTO→Model), repository implementations
- **domain**: Models (data classes), repository interfaces, use cases
- **ui**: Screens (composables), ViewModels (extend `ViewModel`, expose `StateFlow<State>`), UI state sealed classes, navigation graph, components

### Key Patterns

- **DI**: Koin. Each module exports a `val MODULE_NAME = module { ... }` (e.g., `RESUME_MODULE`, `NETWORK_MODULE`). All modules are aggregated in `composeApp/di/KoinModule.kt` as `SHARED_MODULES`.
- **Navigation**: Type-safe routes using `@Serializable` data objects (e.g., `ResumeRoutes.Root`). Each feature provides a `NavGraphBuilder` extension function (e.g., `resumeGraph()`).
- **State**: ViewModels use `MutableStateFlow` with sealed class states (`Initial`, `Loading`, `Success`, `Error`).
- **Platform-specific code**: Uses `expect`/`actual` declarations (e.g., `ResumeRemoteDatasource` URL, settings Koin module). Platform source sets: `androidMain`, `wasmJsMain`.

### Adding a New "App"

1. Create a module under `features/applications/`
2. Extend `Application` abstract class with title, logo, and onClick navigation
3. Create a Koin module and register it in `SHARED_MODULES`
4. Add a navigation graph extension and wire it in `MainNavHost`
5. Add the app instance to the list in `Frame.kt`

## Key Dependencies

| Lib | Purpose |
|---|---|
| Compose Multiplatform 1.10.1 | UI framework |
| Koin 4.1.1 | Dependency injection |
| Ktor 3.4.0 | HTTP client |
| kotlinx.serialization | JSON serialization |
| kotlinx-datetime | Date/time utilities |
| multiplatform-settings | Key-value persistence |
| Kermit | Multiplatform logging |
| Woowla Remix Icons | Icon library |
