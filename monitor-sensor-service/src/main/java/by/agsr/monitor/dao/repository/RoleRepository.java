package by.agsr.monitor.dao.repository;

import by.agsr.monitor.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.role = by.agsr.common.Role.VIEWER")
    Role getViewerRole();
}
