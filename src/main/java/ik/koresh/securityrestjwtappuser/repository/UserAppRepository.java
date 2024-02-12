package ik.koresh.securityrestjwtappuser.repository;

import ik.koresh.securityrestjwtappuser.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepository  extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);
}
