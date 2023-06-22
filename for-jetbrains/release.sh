#!/usr/bin/env bash

source "gradle.properties"

_extract_version() {
  # shellcheck disable=SC2154
  if [[ ${version} =~ ^([0-9]+)\.([0-9]+)\.([0-9]+)-SNAPSHOT$ ]];
  then
      major_version=${BASH_REMATCH[1]}
      minor_version=${BASH_REMATCH[2]}
      patch_version=${BASH_REMATCH[3]}
  else
      echo invalid snapshot version
      exit 1
  fi
}

_upgrade_patch() {
  _extract_version
  echo "version=${major_version}.${minor_version}.$((patch_version + 1))-SNAPSHOT" > "gradle.properties"
}

_execute() {
  command=${1};
  _extract_version
  release_version="${major_version}.${minor_version}.${patch_version}"
  ./gradlew -Pversion="${release_version}" "${command}"
}

set -ex
_execute build
_execute buildPlugin
_execute publishPlugin
_upgrade_patch
