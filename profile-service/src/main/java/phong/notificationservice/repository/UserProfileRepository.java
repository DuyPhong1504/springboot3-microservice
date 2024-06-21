package phong.notificationservice.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import phong.notificationservice.entity.UserProfileEntity;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfileEntity, String> {
}
