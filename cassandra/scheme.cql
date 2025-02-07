CREATE KEYSPACE IF NOT EXISTS blue_tube WITH REPLICATION = {
	'class': 'SimpleStrategy',
	'replication_factor': 1
};

-- Switch to created keyspace
USE blue_tube;

CREATE TABLE IF NOT EXISTS comments_by_user (
	user_id uuid,
	comment_id timeuuid,
	video_id uuid,
	created_at timestamp,
	comment text,
	PRIMARY KEY (user_id, comment_id)
) WITH CLUSTERING ORDER BY (comment_id DESC);

CREATE TABLE IF NOT EXISTS comments_by_video (
	video_id uuid,
	comment_id timeuuid,
	user_id uuid,
	created_at timestamp,
	comment text,
	PRIMARY KEY (video_id, comment_id)
) WITH CLUSTERING ORDER BY (comment_id DESC);
