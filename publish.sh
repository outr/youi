#!/usr/bin/env bash

sbt +clean +test +publishSigned
sbt sonatypeBundleRelease