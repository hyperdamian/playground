package pl.damian.jcassandra;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class JCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(JCassandraApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr(VideoCommentRepository videoCommentRepository) {
		return args -> {
			videoCommentRepository.deleteAll();
			var commentId = Uuids.timeBased();
			long timestamp = Uuids.unixTimestamp(commentId);

			var userComment = new UserComment(UUID.randomUUID(), Uuids.timeBased(), UUID.randomUUID(), Instant.ofEpochMilli(timestamp), "Very nice!");
			var videoComment = new VideoComment(userComment.videoId(), userComment.commentId(), userComment.userId(), userComment.createdAt(), userComment.comment());

			videoComment = videoCommentRepository.save(videoComment);

			videoCommentRepository.findAll()
				.forEach(v -> log.info("Comment: {}", v.comment()));
		};
	}
}
