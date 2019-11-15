#!/usr/bin/env bash

set -e

sbt +clean +test +publishSigned
sbt sonatypeBundleRelease