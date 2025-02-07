package pl.damian.jcassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;
import java.util.UUID;

public interface VideoCommentRepository extends CassandraRepository<VideoComment, UUID> {
	Optional<VideoComment> findByCommentId(UUID commentId);
}
