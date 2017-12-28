package de.uerc.memberarea.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uerc.memberarea.dba.ClubAdminRepository;
import de.uerc.memberarea.dba.ClubMemberRepository;
import de.uerc.memberarea.models.base.ClubUser;
import de.uerc.memberarea.models.base.ClubUser.UserType;

@Service
public class UserService {

    @Autowired
    private ClubAdminRepository clubAdminRepository;

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    public Optional<? extends ClubUser> findById(Long clubUserId, UserType ut) {
        Optional<? extends ClubUser> optIClubUser = null;
        switch (ut) {
        case ADMIN:
            optIClubUser = this.clubAdminRepository.findById(clubUserId);
            break;
        case MEMBER:
        default:
            optIClubUser = this.clubMemberRepository.findById(clubUserId);
            break;
        }
        return optIClubUser;
    }

    public Optional<? extends ClubUser> findUserByUsername(String username, UserType ut) {
        Optional<? extends ClubUser> optClubUser = null;
        switch (ut) {
        case ADMIN:
            optClubUser = this.clubAdminRepository.findByUsername(username);
            break;
        case MEMBER:
        default:
            optClubUser = this.clubMemberRepository.findByUsername(username);
            break;
        }
        return optClubUser;
    }

}
