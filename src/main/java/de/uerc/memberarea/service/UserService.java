package de.uerc.memberarea.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.uerc.memberarea.dba.ClubMemberRepository;
import de.uerc.memberarea.models.users.ClubMember;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	ClubMemberRepository clubMemberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<ClubMember> optClubMember = this.clubMemberRepository.findByUsername(username);
		if (optClubMember.isPresent()) {
			return optClubMember.get();
		}
		throw new UsernameNotFoundException("Not found");
	}

}
