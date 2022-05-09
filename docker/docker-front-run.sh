#!/usr/bin/env bash

docker run \
    --rm \
    --network chagelog-network \
    --name changelog-front \
    -p 8080:80 \
    -d \
    anotheria/changelog-front