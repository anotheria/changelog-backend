#!/usr/bin/env bash

docker run \
    `#--rm` \
    --network chagelog-network \
    --name changelog-postgres \
    -p 6432:5432 \
    -e POSTGRES_USER=changelog \
    -e POSTGRES_PASSWORD=changelog \
    -e POSTGRES_DB=changelog \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    `#-v "/absolute/path/to/directory-with-data":/var/lib/postgresql/data` \
    -d \
    postgres:13.3
