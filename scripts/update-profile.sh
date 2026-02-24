#!/bin/bash
set -e

PROFILE_URL="https://raw.githubusercontent.com/AymericLeFeyer/timelife/refs/heads/main/src/data/profile.json"
YOUTUBE_URL="https://aylabs.fr/youtube-stats.json"
WEB_RESOURCES="composeApp/src/webMain/resources"

mkdir -p "$WEB_RESOURCES"
curl -fsSL "$PROFILE_URL" -o "$WEB_RESOURCES/profile.json"
echo "profile.json updated from timelife"

curl -fsSL "$YOUTUBE_URL" -o "$WEB_RESOURCES/youtube-stats.json"
echo "youtube-stats.json updated from aylabs.fr"
