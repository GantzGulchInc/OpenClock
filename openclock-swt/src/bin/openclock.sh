#!/bin/bash

FULL_PATH_TO_SCRIPT="$(realpath -s "$0")"
SCRIPT_DIRECTORY="$(dirname "$FULL_PATH_TO_SCRIPT")"

java \
    "-Dcom.gantzgulch.openclock.homeDir=${SCRIPT_DIRECTORY}" \
    "-Dcom.gantzgulch.openclock.logLevel=INFO" \
    -jar ${SCRIPT_DIRECTORY}/OpenClock/openclock-swt.jar \
    "$@"
