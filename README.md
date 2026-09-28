# KhuntaLocal — Android app

**Your Local. Your News.**

A hyperlocal, citizen-powered news app for Khunta (Mayurbhanj, Odisha). Every
resident can both read local news and report what is happening around them,
while an editorial verification layer keeps published stories clearly labelled by
their verification status.

This repository currently contains the **Android client** (Phase 1 UI), built
with **Kotlin + Jetpack Compose** and wired to an in-memory mock data source so
the entire app is explorable before the backend exists.

> The backend (Laravel REST API + MySQL) and the admin verification panel are
> planned next — see [Roadmap](#roadmap). The Android data layer is written
> against a `NewsRepository` interface so swapping the mock for a real network
> implementation touches no UI code.

---

## What's implemented

| Area | Screen | Notes |
|------|--------|-------|
| Onboarding | `OnboardingScreen` | Logo, tagline, feature highlights, Get Started / Login |
| Home feed | `HomeScreen` | Branded top bar, breaking-news carousel, category quick-filters, Top Stories |
| Story detail | `NewsDetailScreen` | Hero media, reporter card, body, **verification timeline**, actions |
| Report news | `ReportNewsScreen` | Photo/video/text, headline, description, category, location, language, evidence source |
| Discover | `DiscoverScreen` | Search + category + sort filters |
| Alerts | `AlertsScreen` | Notification centre (breaking, verified, replies, reporter approval…) |
| Profile | `ProfileScreen` | Reporter stats, "My Reports", settings, Become a Verified Reporter |

**Verification-first design:** every story shows a status badge —
`Verified` · `Community Report` · `Under Review` · `Correction` · `Rejected` —
so unverified reports never look like confirmed news. Submitting a report adds it
to the feed as **Under Review**, demonstrating the submit → review → publish
lifecycle end to end against the mock.

## Architecture

```
UI (Compose screens)
  → ViewModel (StateFlow ui-state)
    → NewsRepository (interface)
      → MockNewsRepository (in-memory)     ← swap for a Retrofit-backed impl later
```

- **MVVM** with `ViewModel` + `StateFlow`, collected via `collectAsStateWithLifecycle`.
- **Navigation Compose** with a root graph (onboarding → main → report / detail)
  and a nested bottom-navigation graph (Home / Discover / Alerts / Profile).
- **Manual DI** via `di/ServiceLocator` — no DI framework to configure.
- **Coil** for image loading, with category-tinted placeholders so the feed
  looks intact offline.
- **Material 3** theming with light/dark colour schemes and a brand palette.

### Package layout

```
com.khuntalocal.app
├── data
│   ├── model         // Category, NewsArticle, VerificationStatus, ReportDraft, …
│   ├── remote        // KhuntaApi — contract for the future Laravel API
│   ├── repository    // NewsRepository + MockNewsRepository
│   └── SampleData    // seed content
├── di                // ServiceLocator
└── ui
    ├── theme          // Color, Theme, Type, Shape
    ├── components      // NewsCard, BreakingNewsBanner, VerificationBadge, …
    ├── navigation      // Routes, TabDestination, KhuntaApp (root NavHost)
    └── screens         // onboarding / home / detail / report / discover / alerts / profile
```

## Build & run

Requires **Android Studio (Ladybug or newer)** with the **Android SDK**
(compileSdk 35). JDK 17+.

```bash
# from Android Studio: open the project and Run ▶ the 'app' configuration
# or from the command line (with ANDROID_HOME / local.properties set):
./gradlew :app:assembleDebug
./gradlew :app:installDebug   # to a connected device / emulator
```

The Gradle wrapper (8.14.3), version catalog (`gradle/libs.versions.toml`) and
adaptive launcher icon are all included. Dependency versions:

- Android Gradle Plugin 8.7.3, Kotlin 2.0.21
- Compose BOM 2024.12.01 (Material 3), Navigation Compose 2.8.5
- Lifecycle 2.8.7, Coil 2.7.0
- minSdk 24 · targetSdk / compileSdk 35

> Story thumbnails use `picsum.photos` seed URLs, so they load on a networked
> device and fall back to category-coloured placeholders offline. No API keys
> are needed to run the demo.

## Roadmap

**Phase 1 (this repo):** Onboarding, feed, detail, report, categories, location,
filters, verification-status UI, alerts, profile — against a mock repository.

**Phase 2:** Laravel REST API + MySQL, real auth (mobile OTP), media upload to
object storage, push notifications, reporter verification, comments, saved news,
map view, multilingual content, corrections, duplicate/incident grouping.

**Phase 3:** Editorial admin panel, AI-assisted verification (editor decides, not
the model), video shorts, reputation, analytics, monetization.

## License

TBD.
