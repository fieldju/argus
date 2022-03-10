#!/usr/bin/env bash

set -e

# Change working directory to the location of this script
cd "$(dirname "$0")"

# Build distribution
./gradlew clean bootJar

JAVA_OPT="-Dspring.profiles.active=local"

JVM_ARG="-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n"

# Run
java ${JVM_ARG} -jar ${JAVA_OPT} ./build/libs/sb-app.jar "$@"
