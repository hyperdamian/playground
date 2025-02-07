package pl.damian.jcassandra;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import pl.damian.jcassandra.helper.TimeUuidHelper;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Table("comments_by_user")
public record UserComment(
	@PrimaryKeyColumn(name = "user_id", type = PARTITIONED)
	UUID userId,
	@PrimaryKeyColumn(name = "comment_id", type = CLUSTERED, ordering = DESCENDING)
	UUID commentId,
	@Column("video_id")
	UUID videoId,
	@Column("created_at")
	Instant createdAt,
	String comment
) {

	public UserComment(UUID userId, UUID videoId, TimeUuidHelper.InstantAndUuid instantAndUuid, String comment) {
		this(userId, instantAndUuid.uuid(), videoId, instantAndUuid.instant(), comment);
	}

	private UserComment(UUID userId, UUID commentId, UUID videoId, String comment) {
		this(userId, commentId, videoId, Instant.ofEpochMilli(Uuids.unixTimestamp(commentId)), comment);
	}

}
