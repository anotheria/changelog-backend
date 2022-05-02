CREATE TABLE changelog_tag (
  changelog_id  BIGINT,
  tag           VARCHAR(32) NOT NULL,
  created_at    TIMESTAMP NOT NULL,
  updated_at    TIMESTAMP NOT NULL,

  PRIMARY KEY (changelog_id, tag)
);

