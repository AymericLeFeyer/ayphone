#!/bin/bash
set -e

BASE_URL="https://api.aymeric.lefeyer.fr"
PROFILE_URL="$BASE_URL/api/profile"
COMPANIES_URL="$BASE_URL/api/companies"
TECHNOLOGIES_URL="$BASE_URL/api/technologies"
YOUTUBE_URL="https://aylabs.fr/youtube-stats.json"
RESUME_FILES="features/applications/resume/src/commonMain/composeResources/files"
AYLABS_FILES="features/applications/aylabs/src/commonMain/composeResources/files"

mkdir -p "$RESUME_FILES"
curl -fsSL "$PROFILE_URL" -o "$RESUME_FILES/profile.json"
echo "profile.json updated"

curl -fsSL "$COMPANIES_URL" -o "$RESUME_FILES/companies.json"
echo "companies.json updated"

curl -fsSL "$TECHNOLOGIES_URL" -o "$RESUME_FILES/technologies.json"
echo "technologies.json updated"

mkdir -p "$AYLABS_FILES"
curl -fsSL "$YOUTUBE_URL" -o "$AYLABS_FILES/youtube-stats.json"
echo "youtube-stats.json updated from aylabs.fr"
