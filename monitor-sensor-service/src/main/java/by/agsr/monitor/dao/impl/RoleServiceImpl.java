package by.agsr.monitor.dao.impl;

import by.agsr.monitor.dao.entity.Role;
import by.agsr.monitor.dao.interfaces.RoleService;
import by.agsr.monitor.dao.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public Role getViewerRole() {
        return roleRepository.getViewerRole();
    }
}
