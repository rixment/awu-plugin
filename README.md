# awu - Android Wrapper for Unity

This is an Android's _library_ that is designed to be used as a plugin within Unity's project. It is created using Android Studio 3.6 and should consist of all projects files required by the IDE if one desires to tweak its functionality to his or hers needs.

The main purpose of this _library_ is to allow the user to retreive platform related information within Unity's project and currently includes:

- retreiving information regarding the state of the network via `isNetworkAvailable`
- determining if a device on which the app is running is of Tv-type via `isAndroidTv`

## How to integrate

1. Clone the repository and build the _library_ using Android Studio yourself or use the latest [release](awu/build/outputs/aar/awu-release.aar)

2. Copy the _library_ file into your Unity project's Plugin/Android directory.

3. _(Optional)_ In Unity select the _library's_ file and check __Load on startup__ from the Inspector

4. ...

## Usage

## Copyright and License
Copyright 2005-2020 Rixment. Code released under the [MIT](./LICENSE) license.
