package com.example.attendanceapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.attendanceapp.exceptions.NotYetApprovedException;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.exceptions.UserNotFoundException;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.UserData;
import com.example.attendanceapp.dao.UserDAO;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<UserData> userData = userDAO.findByUemail(email);
		if (userData.isEmpty()) {
			throw new UnauthorizedException();
		}
		UserData user = userData.get();
		if(user.getUapproved().equals("No")){
			throw new NotYetApprovedException();
		}
		ArrayList<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority(user.getUrole()));
		return new User(user.getUemail(), user.getUpassword(), list);
	}

	@Override
	public AuthResponse login(UserData userlogincredentials) {
		final UserDetails userdetails = loadUserByUsername(userlogincredentials.getUemail());
		if (userdetails.getPassword().equals(userlogincredentials.getUpassword())) {
			String email = userdetails.getUsername();
			String role = userdetails.getAuthorities().toArray()[0].toString();
			String generateToken = jwtUtil.generateToken(userdetails);
			UserData user = userDAO.findByUemail(email).get();
			return new AuthResponse(user.getUserid(), true, user.getUfirstname(), generateToken, role, user.getUlastname(),
					user.getUemail(), user.getUage(), user.getUgender(), user.getUcontact());
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public AuthResponse getValidity(String token) {
		AuthResponse res = new AuthResponse();
		if (jwtUtil.validateToken(token)) {
			String userName = jwtUtil.extractUsername(token);
			String password = jwtUtil.extractPassword(token);
			UserData user = userDAO.findByUemailAndUpassword(userName, password).get();
			res.setUid(user.getUserid());
			res.setValid(true);
			res.setFirstname(user.getUfirstname());
			res.setToken(token);
			res.setRole(user.getUrole());
			res.setLastname(user.getUlastname());
			res.setGender(user.getUgender());
			res.setEmail(user.getUemail());
			res.setContact(user.getUcontact());
			res.setAge(user.getUage());
		} else {
			res.setValid(false);
		}
		return res;
	}
	
	@Override
	public void approveAdmin(String email, String token) {
		if (jwtUtil.validateToken(token)) {
			String userName = jwtUtil.extractUsername(token);
			String password = jwtUtil.extractPassword(token);
			UserData user = userDAO.findByUemailAndUpassword(userName, password).get();
			if(user.getUrole().equals("SuperUser")) {
				try{
					UserData userData = userDAO.findByUemail(email).get();
					userData.setUapproved("Yes");
					userDAO.save(userData);				
				}
				catch(Exception e) {
					throw new UserNotFoundException();
				}
			}
			else {
				throw new UnauthorizedException();
			}
		}
		else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public AuthResponse register(UserData userCredentials) {
		userDAO.save(userCredentials);
		if(userCredentials.getUapproved().equals("Yes")) {
			return login(userCredentials);		
		}
		else {
			return null;
		}
	}

	@Override
	public List<String> getAllUserIds(String token) {
		List<UserData> userList = userDAO.findAll();
		return userList.stream()
				.map(user -> user.getUserid())
				.collect(Collectors.toList());
	}

}
