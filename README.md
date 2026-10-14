# GlideCoilPicassoRoom

An Android sandbox for getting to know the image loading libraries
(Glide, Coil, Picasso) and the Room database. Everything lives in a single `MainActivity`.

## What's inside

- **Room** (active code): a `Cat` entity, a DAO with queries (all records, names,
  search by name, age range and age list), and the `cat_db` database. On launch,
  test records are written to the database on a background thread and the query
  result is printed to the log; the code also includes an example `1 → 2` migration
  (adding a column).
- **Glide, Coil, Picasso**: examples of loading an image and a GIF into an `ImageView`
  with transformations (crossfade, rounded corners, circle crop, grayscale).
  They are left commented out: to try a particular library, uncomment
  its block in `MainActivity`.

## Tech stack

| Area      | Technologies              |
|-----------|---------------------------|
| Language  | Kotlin                    |
| Images    | Glide, Coil (+ coil-gif), Picasso |
| Storage   | Room (schema is exported to `app/schemas`) |
| UI        | ViewBinding               |

## Build

Requires JDK 11–17 and Android SDK 31.

```bash
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
