package org.project.repository;

import org.project.document.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findByAuthId(Long id);

    Page<UserProfile> findByAuthIdNot (Pageable page, Long authId);
}
