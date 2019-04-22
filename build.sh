#!/bin/bash
set -e
gopherjs build web/web.go -o build/soyuz.js
gomobile bind -target ios -o build/Soyuz.framework
GOOS=js GOARCH=wasm go build -o build/soyuz.wasm