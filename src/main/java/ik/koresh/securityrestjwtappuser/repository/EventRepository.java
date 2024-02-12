package ik.koresh.securityrestjwtappuser.repository;

import ik.koresh.securityrestjwtappuser.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
