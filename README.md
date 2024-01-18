# Sleeping Server Stopper
This mod is a port of the [Sleeping Server Stopper Spigot plugin](https://github.com/vincss/mcEmptyServerStopper) for Forge 1.20 (Haven't checked other versions. Might work).

This is meant to be used with the mod [SleepingServerStarter](https://github.com/vincss/mcsleepingserverstarter) by [vincss](https://github.com/vincss).

## 🤝 Contributers
Author: [MadaHaz](https://github.com/MadaHaz)

I'm basing my mod off of another port which has been archived, [EmptyServerStopper](https://github.com/GHYAKIMA/emptyserverstopper-mod) by [GHYAKIMA](https://github.com/GHYAKIMA). I couldn't properly fork their project, they had some dual development setup for both Fabric and Forge, couldn't make any sense of it, so I decided to remake it myself and add some features.

## 🛠 TODO
- ~~Add config file.~~ DONE

## 📄 Versions
- v2.0.0
  - Forge 1.20.1 (Will probably work on other versions. Haven't checked.)
  - Coded in Forge 1.20.1 47.2.0.
  - Config File is generated. Values that can be changed are:
    - SHUTDOWN_TIME_IN_MINUTES - How many minutes until the server closes. (Minimum is 1 minute!)
    - SHUTDOWN_SERVER_ON_LAUNCH - Should the server shutdown on launch?
  - Default config values are (2, true).
  - Fixed 2 minor bugs.
- v1.0.0
  - Forge 1.20.1 (Will probably work on other versions. Haven't checked.)
  - Coded in Forge 1.20.1 47.2.0.
  - Hard coded values. 2 minute timer and the server will close on startup.
