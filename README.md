<div align="center">

# 🔐 Password Generator

**Una aplicación de escritorio para crear contraseñas a tu medida.**

Elige los tipos de caracteres, indica la longitud y genera una contraseña en un clic. También puedes partir de una palabra clave y copiar el resultado al portapapeles.

[Descargar para Windows](https://github.com/guilleWR/java-password-generator/releases) · [Ver cómo se usa](#-cómo-se-usa) · [Compilar desde el código](#-compilar-desde-el-código)

</div>

## ✨ Qué puedes hacer

| Opción | Descripción |
| --- | --- |
| 🔤 Letras | Incluir minúsculas y/o mayúsculas. |
| 🔢 Números y símbolos | Activar los grupos de caracteres que quieras. |
| 📏 Longitud | Elegir el tamaño de una contraseña generada sin palabra clave. |
| 🔑 Palabra clave | Usarla como base; la aplicación transforma algunos caracteres según las opciones elegidas. |
| 📋 Copiar | Llevar el resultado al portapapeles con un botón. |

## 🖼️ Capturas

> Espacio reservado para tus capturas. Guarda las imágenes en `docs/screenshots/` y sustituye el texto de la tabla por imágenes Markdown.

| Pantalla principal | Contraseña generada |
| :---: | :---: |
| 📸 `docs/screenshots/pantalla-principal.png` | 📸 `docs/screenshots/resultado.png` |

<!-- Ejemplo para sustituir una celda cuando tengas la captura:
![Pantalla principal](docs/screenshots/pantalla-principal.png)
-->

## 🧰 Tecnologías

| Java 21 | JavaFX 21 |
| :---: | :---: |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" alt="Logo de Java" width="72" height="72"> | <img src="https://upload.wikimedia.org/wikipedia/commons/3/30/JavaFX_text_logo.png" alt="Logo de JavaFX" width="150"> |
| Lógica de la aplicación | Interfaz de escritorio con FXML y CSS |

El proyecto usa **Maven** para gestionar las dependencias y **`jpackage`** para crear el ejecutable de Windows con su propio runtime. Los logotipos proceden de [Devicon](https://github.com/devicons/devicon) y [Wikimedia Commons](https://commons.wikimedia.org/wiki/File:JavaFX_text_logo.png).

## 🪟 Descargar y ejecutar en Windows

1. Abre la página de [Releases](https://github.com/guilleWR/java-password-generator/releases) y descarga `PasswordGenerator-Windows-x64.zip` de la versión publicada.
2. Extrae **todo** el ZIP en una carpeta.
3. Abre `PasswordGenerator/PasswordGenerator.exe`.

La distribución incluye Java y JavaFX. **No necesitas instalarlos** para usar la aplicación. Conserva juntos el `.exe` y las carpetas `app` y `runtime` que vienen en el ZIP.

> El ZIP debe subirse como archivo adjunto de una GitHub Release. El código descargado con «Code → Download ZIP» no contiene el ejecutable.

## 🚀 Cómo se usa

1. Marca al menos un tipo de carácter: minúsculas, mayúsculas, números o caracteres especiales.
2. Si no usas una palabra clave, indica la longitud deseada.
3. Opcionalmente, escribe una palabra clave. La aplicación intenta transformar sus caracteres según las opciones marcadas; los que no tienen una sustitución válida se conservan. En este modo, el resultado tiene la longitud de la palabra clave.
4. Pulsa **Generate password** y, si quieres, **Copy to clipboard**.

## 🛠️ Compilar desde el código

Necesitas **Windows**, **JDK 21** (por ejemplo, [Eclipse Temurin 21](https://adoptium.net/temurin/releases/?version=21)) y conexión a Internet en la primera compilación. Maven y JavaFX se descargan mediante el Maven Wrapper y el `pom.xml` del proyecto.

```powershell
git clone https://github.com/guilleWR/java-password-generator.git
cd java-password-generator
powershell -ExecutionPolicy Bypass -File .\build-exe.ps1
```

El resultado estará en `dist/PasswordGenerator/PasswordGenerator.exe`. Para volver a ejecutar el script, renombra o elimina antes la carpeta `dist/PasswordGenerator` anterior.

### Preparar una descarga para GitHub Releases

Desde PowerShell, después de compilar:

```powershell
Compress-Archive -Path .\dist\PasswordGenerator -DestinationPath .\dist\PasswordGenerator-Windows-x64.zip -Force
```

Sube `dist/PasswordGenerator-Windows-x64.zip` como archivo adjunto de una **Release**. La carpeta `dist/` está ignorada por Git porque los binarios se distribuyen allí, no como archivos del código fuente.
