param(
    [string]$JdkHome = $env:JAVA_HOME
)

$ErrorActionPreference = 'Stop'
Set-Location $PSScriptRoot

if (-not $JdkHome -or -not (Test-Path (Join-Path $JdkHome 'bin\jpackage.exe'))) {
    $JdkHome = Get-ChildItem 'C:\Program Files\Eclipse Adoptium' -Directory -ErrorAction SilentlyContinue |
        Where-Object Name -Like 'jdk-21*' |
        Sort-Object Name -Descending |
        Select-Object -First 1 -ExpandProperty FullName
}
if (-not $JdkHome -or -not (Test-Path (Join-Path $JdkHome 'bin\jpackage.exe'))) {
    throw 'Se necesita un JDK 21. Instala Eclipse Temurin 21 o indica -JdkHome.'
}

$env:JAVA_HOME = $JdkHome
& .\mvnw.cmd -B package dependency:copy-dependencies '-DincludeScope=runtime' '-DoutputDirectory=target/dependency'
if ($LASTEXITCODE -ne 0) { throw 'La compilacion Maven ha fallado.' }

$modules = Join-Path $PSScriptRoot 'target\package-modules'
New-Item -ItemType Directory -Force $modules | Out-Null
Copy-Item 'target\PasswordGenerator-1.0-SNAPSHOT.jar' $modules -Force
Copy-Item 'target\dependency\*-win.jar' $modules -Force
Copy-Item 'target\dependency\bootstrapfx-core-0.4.0.jar' $modules -Force

$output = Join-Path $PSScriptRoot 'dist\PasswordGenerator'
if (Test-Path $output) {
    throw "Ya existe $output. Renombra o elimina esa carpeta antes de volver a empaquetar."
}

& (Join-Path $JdkHome 'bin\jpackage.exe') --type app-image --name PasswordGenerator --module-path $modules --module es.gui.passwordgenerator/es.gui.passwordgenerator.Application --dest dist --app-version 1.0 --vendor PasswordGenerator
if ($LASTEXITCODE -ne 0) { throw 'jpackage ha fallado.' }
Write-Host "Ejecutable creado: $output\PasswordGenerator.exe"
Write-Host 'Distribuye la carpeta PasswordGenerator completa.'
