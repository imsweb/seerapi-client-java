#!/bin/bash
# This script initiates the Gradle publishing task when pushes to master occur.  This was borrowed
# from https://github.com/ReadyTalk/swt-bling/blob/master/.utility/initiate-publish.sh.
#
# NOTE: Travis-CI can only publish SNAPSHOT versions.

if [ "$TRAVIS_REPO_SLUG" == "imsweb/seerapi-client-java" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  if [[ $(./gradlew -q getVersion) != *SNAPSHOT* ]]; then
      echo 'Travis can only publish snapshots.'
      exit 0
  fi

  echo -e "Starting publish to Sonatype...\n"

  ./gradlew uploadArchives -PnexusUsername="${NEXUS_USERNAME}" -PnexusPassword="${NEXUS_PASSWORD}"
  RETVAL=$?

  if [ $RETVAL -eq 0 ]; then
    echo 'Completed publish!'
  else
    echo 'Publish failed.'
    exit 1
  fi

fi