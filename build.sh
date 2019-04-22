#!/bin/bash
set -e

# Build iOS
cd mobile
gomobile bind -target ios -o ../build/Soyuz.framework -prefix SZ
ANDROID_HOME=$HOME/Library/Android/sdk gomobile bind -target android -o ../build/soyuz.aar
cd ..

# Build Web
cd web
gopherjs build -o ../build/soyuz.js
cd ..

# Build WASM
cd wasm
GOOS=js GOARCH=wasm go build -o ../build/soyuz.wasm
cd ..