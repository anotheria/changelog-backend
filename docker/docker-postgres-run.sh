#!/usr/bin/env bash

docker run \
    --name changelog-postgres \
    -p 5433:5433 \
    -e POSTGRES_USER=changelog \
    -e POSTGRES_PASSWORD=changelog \
    -e POSTGRES_DB=changelog \
    -e PGDATA=/Users/ano/IdeaProjects/changelog/changelog-postgres/data/pgdata \
    -d \
    postgres:13.3