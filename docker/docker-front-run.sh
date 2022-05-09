#!/usr/bin/env bash

docker run \
    --rm \
    --network chagelog-network \
    --name changelog-ui \
    -p 8080:80 \
    -d \
    anotheria/changelog-ui