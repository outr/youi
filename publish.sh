#!/usr/bin/env bash

set -e

sbt +clean +compile +publishSigned
sbt sonatypeBundleRelease