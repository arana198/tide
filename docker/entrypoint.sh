#!/bin/sh
set -e
set -x
export DEBIAN_FRONTEND=noninteractive

cd /opt/webapp
java -jar tide-test.jar