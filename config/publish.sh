#!/bin/bash
# NOTE: Snapshot versions are not uploaded to bintray

if [ "$TRAVIS_REPO_SLUG" == "imsweb/seerapi-client-java" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  if [[ $(./gradlew -q getVersion) == *SNAPSHOT* ]]; then
      echo 'Bintray cannot publish snapshots.'
      exit 0
  fi

  echo -e "Starting publish to Bintray...\n"

  ./gradlew bintrayUpload
  RETVAL=$?

  if [ $RETVAL -eq 0 ]; then
    echo 'Completed publish!'
  else
    echo 'Publish failed.'
    exit 1
  fi

fi