#!/usr/bin/env bash

docker run \
    --rm \
    --network chagelog-network \
    --name changelog-postgres \
    -p 6432:5432 \
    -e POSTGRES_USER=changelog \
    -e POSTGRES_PASSWORD=changelog \
    -e POSTGRES_DB=changelog \
    -e PGDATA=/Users/ano/IdeaProjects/changelog/changelog-postgres/data/pgdata \
    -d \
    postgres:13.3
