CREATE TABLE IF NOT EXISTS announcements (
  id         VARCHAR(36) NOT NULL,
  created_ts TIMESTAMP   NOT NULL DEFAULT now(),
  updated_ts TIMESTAMP   NOT NULL DEFAULT now(),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS announcement_votes (
  id              VARCHAR(36) NOT NULL,
  announcement_id VARCHAR(36) NOT NULL,
  like_count      INT(11)     NOT NULL,
  dislike_count   INT(11)     NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_announcements_announcement_votes FOREIGN KEY (announcement_id) REFERENCES announcements (id)
);
