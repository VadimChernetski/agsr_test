package by.agsr.common;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMINISTRATOR,
    VIEWER;

    @Override
    public String getAuthority() {
        return super.name();
    }

}
