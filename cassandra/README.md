# Starting Docker Compose

```shell
docker compose up -d
```

# Connecting with cqlsh

```shell
docker exec -it cassandra-play cqlsh
```

# Load data from file

```shell
docker exec -i cassandra-play cqlsh < ./scheme.cql
```

# Learning

primary key -- a unique identifier
partition key -- used to distribute data across all nodes
clustering key -- used to sort data within a partition

# Show metadata

`
DESCRIBE KEYSPACES | {keyspace} | {keyspace.table}
`
