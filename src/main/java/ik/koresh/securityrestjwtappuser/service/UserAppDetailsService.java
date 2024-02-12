package ik.koresh.securityrestjwtappuser.service;

import ik.koresh.securityrestjwtappuser.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAppDetailsService implements UserDetailsService {

    private final UserAppRepository userAppRepository;

    @Autowired
    public UserAppDetailsService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository.findByUsername(username).orElseThrow();
    }
}
