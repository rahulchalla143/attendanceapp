package com.example.attendanceapp.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.attendanceapp.dao.UserDAO;
import com.example.attendanceapp.exceptions.NotYetApprovedException;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.exceptions.UserAlreadyExistsException;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.UserData;
import com.example.attendanceapp.service.AuthenticationServiceImpl;
import com.example.attendanceapp.service.JwtUtil;

@SpringBootTest
public class AuthenticationServiceImplTest {

	@InjectMocks
	AuthenticationServiceImpl authServiceImpl;

	@Mock
	UserDAO userdao;

	@Mock
	JwtUtil jwtutil;

	@Test
	void loadUserByUsernameTest() {
		UserData user1 = new UserData("ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "Yes");

		Optional<UserData> data = Optional.of(user1);
		when(userdao.findByUemail("ab")).thenReturn(data);

		UserDetails userdata = authServiceImpl.loadUserByUsername("ab");
		assertEquals("ab", userdata.getUsername());
	}

	@Test
	void loadUserByUsernameFailedTest() {
		UserData user1 = new UserData("ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "Yes");

		Optional<UserData> data = Optional.of(user1);
		when(userdao.findByUemail("ab")).thenReturn(data);

		UserDetails userdata = authServiceImpl.loadUserByUsername("ab");
		assertThrows(UnauthorizedException.class, () -> authServiceImpl.loadUserByUsername("a"));
	}

	@Test
	void loginTest() {
		UserData user1 = new UserData("ab", "ab", "ab", "Role", "ab", "ab", "ab", "ab", "ab", "Yes");
		Optional<UserData> data = Optional.of(user1);
		when(userdao.findByUemail("ab")).thenReturn(data);
		when(userdao.findByUemailAndUpassword("ab","ab")).thenReturn(data);
		UserDetails value = authServiceImpl.loadUserByUsername("ab");
		when(jwtutil.generateToken(value)).thenReturn("token");
		UserData result = new UserData("ab", "ab", "ab", "Role", "ab", "ab", "ab", "ab", "ab", "ab");
		when(jwtutil.generateToken(value)).thenReturn("token");
		assertEquals(authServiceImpl.login(user1).getFirstname(), result.getUfirstname());
	}

	@Test
	void loginFailedTest() {
		UserData user1 = new UserData("ab", "ab", "ab", "Role", "ab", "ab", "ab", "ab", "ab", "ab");
		UserData user2 = new UserData("ab", "ab", "ab", "Role", "ab", "p", "ab", "ab", "ab", "ab");
		Optional<UserData> data = Optional.of(user1);
		when(userdao.findByUemail("ab")).thenReturn(data);
		UserDetails value = authServiceImpl.loadUserByUsername("ab");
		when(jwtutil.generateToken(value)).thenReturn("token");
		UserData result = new UserData("ab", "ab", "ab", "Role", "ab", "ab", "ab", "ab", "ab", "ab");
		when(jwtutil.generateToken(value)).thenReturn("token");
		assertThrows(UnauthorizedException.class, () -> authServiceImpl.login(user2));
	}

	@Test
	void testGetValidity() {
		UserData user = new UserData("ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab");
		Optional<UserData> data = Optional.of(user);
		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("ab");
		when(jwtutil.extractPassword("token")).thenReturn("ab");
		when(userdao.findByUemailAndUpassword("ab", "ab")).thenReturn(data);
		AuthResponse auth = new AuthResponse("ad", true, "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab");
		assertEquals(authServiceImpl.getValidity("token").getEmail(), auth.getEmail());
	}

	@Test
	void getValidityFailedTest() {
		UserData user = new UserData("ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab");
		Optional<UserData> data = Optional.of(user);
		when(jwtutil.validateToken("token")).thenReturn(false);
		when(jwtutil.extractUsername("token")).thenReturn("ab");
		when(jwtutil.extractPassword("token")).thenReturn("ab");
		when(userdao.findByUemailAndUpassword("ab", "ab")).thenReturn(data);
		assertEquals(authServiceImpl.getValidity("token").getEmail(), null);
	}

	@Test
	void testapproveAdmin() {
		UserData user = new UserData("ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab", "Yes");
		Optional<UserData> data = Optional.of(user);
		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("ab");
		when(jwtutil.extractPassword("token")).thenReturn("ab");
		when(userdao.findByUemailAndUpassword("ab", "ab")).thenReturn(data);
		when(userdao.findByUemail("ab")).thenReturn(data);
		AuthResponse auth = new AuthResponse("ad", true, "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab");
		assertEquals(authServiceImpl.getValidity("token").getEmail(), auth.getEmail());
	}

	@Test
	void testRegister() {

		UserData user1 = new UserData("ab", "ab", "ab", "User", "ab", "ab", "ab", "ab", "ab", "Yes");
		Optional<UserData> nulldata = Optional.empty();
		Optional<UserData> data = Optional.of(user1);
		when(userdao.findByUemail("ab")).thenReturn(nulldata).thenReturn(data);
		when(userdao.findByUemailAndUpassword("ab","ab")).thenReturn(data);
		UserData result = new UserData("ab", "ab", "ab", "User", "ab", "ab", "ab", "ab", "ab", "Yes");
		assertEquals(authServiceImpl.register(user1).getFirstname(), result.getUfirstname());
	}
	
	@Test
	void testRegisterFailed() {

		UserData user1 = new UserData("ab", "ab", "ab", "User", "ab", "ab", "ab", "ab", "ab", "Yes");
		Optional<UserData> data = Optional.of(user1);
		when(userdao.findByUemail("ab")).thenReturn(data);
		assertThrows(UserAlreadyExistsException.class, ()->authServiceImpl.register(user1));
	}

	@Test
	void testRegisterForAdmin() {
		UserData user1 = new UserData("ab", "ab", "ab", "Admin", "ab", "ab", "ab", "ab", "ab", "No");
		assertNull(authServiceImpl.register(user1));
	}

	@Test
	void UserNotYetApprovedtest() {
		UserData user1 = new UserData("ab", "ab", "ab", "Admin", "ab", "ab", "ab", "ab", "ab", "No");
		Optional<UserData> data = Optional.of(user1);
		when(userdao.findByUemail("ab")).thenReturn(data);
		when(userdao.findByUemailAndUpassword("ab","ab")).thenReturn(data);
		assertThrows(NotYetApprovedException.class, () -> authServiceImpl.login(user1));
	}

}
