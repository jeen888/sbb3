#!/bin/bash

./gradlew clean build -x test && ./gradlew bootRun
