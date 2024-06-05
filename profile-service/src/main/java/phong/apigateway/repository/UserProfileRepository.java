package phong.apigateway.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import phong.apigateway.entity.UserProfileEntity;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfileEntity, String> {
}
