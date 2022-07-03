package com.service.CompanyX.repositories;

import com.service.CompanyX.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findProfileByEmail(String email);


}
