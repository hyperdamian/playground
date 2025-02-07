package pl.damian.jcassandra.helper;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import java.time.Instant;
import java.util.UUID;

public class TimeUuidHelper {

	/**
	 * Generate version 1 UUID and prepare corresponding Instant
	 */
	public static InstantAndUuid pepareInstantAndUuid() {
		var uuid = Uuids.timeBased();
		long timestamp = Uuids.unixTimestamp(uuid);
		return new InstantAndUuid(uuid, Instant.ofEpochMilli(timestamp));
	}

	public record InstantAndUuid(UUID uuid, Instant instant) {
	}

}
