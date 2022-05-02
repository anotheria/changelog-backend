#!/usr/bin/env bash

docker run \
    --rm \
    --network chagelog-network \
    --name changelog-extapi \
    -p 8080:8080 \
    -e CONFIGUREME_ENVIRONMENT=dev_gsokolov_docker \
    -d \
    anotheria/changelog
