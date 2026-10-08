Languages: **English** | [Español](README.es.md)

<div align="center">

# Password Generator

**A simple desktop app for creating custom passwords.**

Choose character types, set a length, and generate a password. You can also start with a keyword and copy the result to your clipboard.

[Download for Windows](https://raw.githubusercontent.com/guilleWR/java-password-generator/main/downloads/PasswordGenerator-Windows-x64.zip) · [How to use](#how-to-use)

</div>

## Features

| Feature | Description |
| --- | --- |
| Character choices | Include lowercase letters, uppercase letters, numbers, and special characters. |
| Custom length | Set the length when generating a password without a keyword. |
| Optional keyword | Transform similar characters according to your selected options. |
| Clipboard | Copy the generated password with one click. |

## Screenshots

| Main window | Generated password |
| :---: | :---: |
| ![Main window](docs/screenshots/pantalla-principal.png) | ![Generated password](docs/screenshots/resultado.png) |

## Technologies

<p>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" width="56" height="56" alt="Java" title="Java" />
  &nbsp;&nbsp;&nbsp;
  <img src="https://upload.wikimedia.org/wikipedia/commons/3/30/JavaFX_text_logo.png" width="132" height="56" alt="JavaFX" title="JavaFX" />
</p>

Java 21 · JavaFX 21 · Maven · FXML · CSS · jpackage

The project uses **Maven** to manage dependencies and **`jpackage`** to bundle the Windows launcher with its runtime.

## Download and run on Windows

1. [Download the Windows x64 ZIP](https://raw.githubusercontent.com/guilleWR/java-password-generator/main/downloads/PasswordGenerator-Windows-x64.zip).
2. Extract the **entire** ZIP.
3. Open `PasswordGenerator/PasswordGenerator.exe` inside the extracted folder.

You do **not** need to install Java or JavaFX. The ZIP contains the app and its Java runtime. Keep the `.exe`, `app`, and `runtime` together; the `.exe` alone will not run.

## How to use

1. Select at least one character type: lowercase, uppercase, numbers, or special characters.
2. If you are not using a keyword, enter the desired password length.
3. Optionally, enter a keyword. The app transforms characters when a similar replacement matches your selections; otherwise it keeps the original character. In keyword mode, the result has the keyword's length.
4. Click **Generate password**, then **Copy to clipboard** if needed.

## Build from source

Building requires **Windows** and **JDK 21** (for example, [Eclipse Temurin 21](https://adoptium.net/temurin/releases/?version=21)). The Maven Wrapper downloads Maven and JavaFX dependencies on the first build.

```powershell
git clone https://github.com/guilleWR/java-password-generator.git
cd java-password-generator
powershell -ExecutionPolicy Bypass -File .\build-exe.ps1
```

The result is `dist/PasswordGenerator/PasswordGenerator.exe`. Rename or remove the previous `dist/PasswordGenerator` folder before running the script again. To update the downloadable ZIP after rebuilding, compress the whole `dist/PasswordGenerator` folder and replace `downloads/PasswordGenerator-Windows-x64.zip`.
