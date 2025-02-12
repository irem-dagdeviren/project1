package org.project.repository;

import org.project.document.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    UserProfile findByAuthId(Long id);
}
