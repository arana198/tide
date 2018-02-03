CREATE TABLE IF NOT EXISTS announcements (
  id         VARCHAR(36) NOT NULL,
  created_ts TIMESTAMP   NOT NULL   DEFAULT now(),
  updated_ts TIMESTAMP   NOT NULL   DEFAULT now(),
  version    INT(11)     NOT NULL   DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS announcement_votes (
  id              VARCHAR(36) NOT NULL,
  announcement_id VARCHAR(36) NOT NULL,
  like_count      INT(11)     NOT NULL,
  dislike_count   INT(11)     NOT NULL,
  version         INT(11)     NOT NULL   DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT fk_announcements_announcement_votes FOREIGN KEY (announcement_id) REFERENCES announcements (id)
);
