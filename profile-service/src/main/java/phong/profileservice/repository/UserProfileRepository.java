package phong.profileservice.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import phong.profileservice.entity.UserProfileEntity;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfileEntity, String> {
}
