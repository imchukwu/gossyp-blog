package com.cimspace.gossypblog;

import com.cimspace.gossypblog.model.Role;
import com.cimspace.gossypblog.model.User;
import com.cimspace.gossypblog.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;

@SpringBootApplication
public class GossypBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GossypBlogApplication.class, args);
	}

	public void authenthicationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		if(repo.count() == 0)
			repo.save(new User("user","user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		builder.userDetailsService(new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
				return new CustomUserDetails(repo.findByUsername(s));
			}

		});
	}
}
