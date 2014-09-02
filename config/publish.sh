#!/bin/bash
# This script initiates the Gradle publishing task when pushes to master occur.  This was borrowed
# from https://github.com/ReadyTalk/swt-bling/blob/master/.utility/initiate-publish.sh.
#
# NOTE: Snapshot versions cannot be uploaded to bintray

if [ "$TRAVIS_REPO_SLUG" == "imsweb/seerapi-client-java" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  if [[ $(./gradlew -q getVersion) == *SNAPSHOT* ]]; then
      echo 'Bintray cannot publish snapshots.'
      exit 0
  fi

  echo -e "Starting publish to Sonatype...\n"

  ./gradlew uploadBintray -PbintrayUser="${BINTRAY_USER}" -PbintrayKey="${BINTRAY_KEY}"
  RETVAL=$?

  if [ $RETVAL -eq 0 ]; then
    echo 'Completed publish!'
  else
    echo 'Publish failed.'
    exit 1
  fi

fi