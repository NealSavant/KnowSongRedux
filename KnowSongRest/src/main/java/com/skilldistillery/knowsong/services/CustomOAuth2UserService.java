package com.skilldistillery.knowsong.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.skilldistillery.knowsong.entities.Rank;
import com.skilldistillery.knowsong.entities.User;
import com.skilldistillery.knowsong.repositories.RankRepository;
import com.skilldistillery.knowsong.repositories.UserRepository;

@Component
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RankRepository rankRepo;
	
	// user must go through this method every time they authenticate with the server (login / use the app)
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		//create new user object and get the attributes
		OAuth2User oauthUser = super.loadUser(userRequest);
		Map<String, Object> attributes = oauthUser.getAttributes();
		System.out.println(attributes);

		String username = oauthUser.getName();
		String imgSource = null;
		try {
			// Map -> ArrayList -> LinkedHashMap -> String
			imgSource = ( (LinkedHashMap<String,String>) ((ArrayList<LinkedHashMap<String,String>>) attributes.get("images")).get(0)).get("url");	// get first image for your spotify account..
		}catch(Exception e) {
			System.err.println("user has no profile photo, not sure what error will look like. feel free to delete this ");
			e.printStackTrace();
		}

		User user = userRepo.findByUsername(username);
		
		//check if user is in database, register new user or update image
		if(user != null) {
			user.setImgSource(imgSource);	
			userRepo.saveAndFlush(user);
		}else {
			User newUser = new User();
			newUser.setEnabled(true);
			newUser.setRole("standard");
			newUser.setImgSource(imgSource);
			newUser.setUsername(username);
			Rank rank = rankRepo.findById(1).get();
			newUser.setRank(rank);
			userRepo.saveAndFlush(newUser);
		}
		return oauthUser;
	}
	
}
