# fixed-screenshot-utility

A lightweight Java utility for capturing a fixed screen region to the clipboard with a single hotkey.

## Features

- **Fixed-area capture** - Define coordinates once, use forever
- **Global hotkey** - `Win + Shift + F` works everywhere (even when minimized)
- **Clipboard integration** - Screenshots ready to paste instantly
- **System tray** - Runs quietly in the background
- **Configurable** - Simple text file configuration

## Usage
1. **Run** the application (`fixed-screenshot-utility.jar`).
2. **Configure** your capture area by editing `config.properties`.
3. **Restart** the app.
4. **Press** `Win + Shift + F` to capture
5. **Press** anywhere with `Ctrl + V`

## Requirements
- Java 11+
- Windows (Linux/Mac support via JNativeHook, but untested)

## Tech Stack
- Java AWT Robot for screen capture
- JNativeHook for global keyboard listening
- Maven for build management
