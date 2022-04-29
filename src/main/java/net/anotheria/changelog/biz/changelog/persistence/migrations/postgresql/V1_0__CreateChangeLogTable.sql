CREATE TABLE changelog (
  id            SERIAL PRIMARY KEY,

  author        VARCHAR(32) NOT NULL,
  message       VARCHAR(128),
  reason        VARCHAR(32) NOT NULL,
  type          VARCHAR(32) NOT NULL,
  time_when     TIMESTAMP NOT NULL,

  created_at    TIMESTAMP NOT NULL,
  updated_at    TIMESTAMP NOT NULL
);

