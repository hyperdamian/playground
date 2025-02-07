package pl.damian.jcassandra;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import pl.damian.jcassandra.helper.TimeUuidHelper;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Table("comments_by_video")
public record VideoComment(
	@PrimaryKeyColumn(name = "video_id", type = PARTITIONED)
	UUID videoId,
	@PrimaryKeyColumn(name = "comment_id", type = CLUSTERED, ordering = DESCENDING)
	UUID commentId,
	@Column("user_id")
	UUID userId,
	@Column("created_at")
	Instant createdAt,
	String comment
) {

	public VideoComment(UUID videoId, UUID userId, TimeUuidHelper.InstantAndUuid instantAndUuid, String comment) {
		this(videoId, instantAndUuid.uuid(), userId, instantAndUuid.instant(), comment);
	}

}
