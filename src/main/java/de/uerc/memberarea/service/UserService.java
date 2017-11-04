package de.uerc.memberarea.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uerc.memberarea.dba.ClubAdminRepository;
import de.uerc.memberarea.dba.ClubMemberRepository;
import de.uerc.memberarea.models.SocialClub;
import de.uerc.memberarea.models.users.IClubUser;
import de.uerc.memberarea.models.users.IClubUser.UserType;

@Service
public class UserService {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private ClubAdminRepository clubAdminRepository;

    public Optional<? extends IClubUser> findUserByUsernameAndSocialClub(String username, SocialClub sc, UserType ut) {
        Optional<? extends IClubUser> optIClubUser = null;
        switch (ut) {
        case ADMIN:
            optIClubUser = this.clubAdminRepository.findByUsernameAndSocialClub(username, sc);
            break;
        case MEMBER:
        default:
            optIClubUser = this.clubMemberRepository.findByUsernameAndSocialClub(username, sc);
            break;
        }
        return optIClubUser;
    }

    public Optional<? extends IClubUser> findById(Long iClubUserId, UserType ut) {
        Optional<? extends IClubUser> optIClubUser = null;
        switch (ut) {
        case ADMIN:
            optIClubUser = this.clubAdminRepository.findById(iClubUserId);
            break;
        case MEMBER:
        default:
            optIClubUser = this.clubMemberRepository.findById(iClubUserId);
            break;
        }
        return optIClubUser;
    }

}
