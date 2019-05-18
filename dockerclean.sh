#!/bin/bash
CONTAINER_NAME1="jhipster"
OLD1="$(docker ps --all --quiet --filter=name="$CONTAINER_NAME1")"
if [ -n "$OLD1" ]; then
  docker stop $OLD1 && docker rm $OLD1
fi
CONTAINER_NAME2="elasticsearch"
OLD2="$(docker ps --all --quiet --filter=name="$CONTAINER_NAME2")"
if [ -n "$OLD2" ]; then
  docker stop $OLD2 && docker rm $OLD2
fi
CONTAINER_NAME3="postgresql"
OLD3="$(docker ps --all --quiet --filter=name="$CONTAINER_NAME3")"
if [ -n "$OLD3" ]; then
  docker stop $OLD3 && docker rm $OLD3
fi
