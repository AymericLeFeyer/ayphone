#!/bin/bash
set -e

PROFILE_URL="https://raw.githubusercontent.com/AymericLeFeyer/timelife/refs/heads/main/src/data/profile.json"
YOUTUBE_URL="https://aylabs.fr/youtube-stats.json"
RESUME_FILES="features/applications/resume/src/commonMain/composeResources/files"
AYLABS_FILES="features/applications/aylabs/src/commonMain/composeResources/files"

mkdir -p "$RESUME_FILES"
curl -fsSL "$PROFILE_URL" -o "$RESUME_FILES/profile.json"
echo "profile.json updated from timelife"

mkdir -p "$AYLABS_FILES"
curl -fsSL "$YOUTUBE_URL" -o "$AYLABS_FILES/youtube-stats.json"
echo "youtube-stats.json updated from aylabs.fr"
