package com.example.attendanceapp.dao.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.attendanceapp.dao.UserDAO;
import com.example.attendanceapp.model.UserData;


@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class UserDAOTest {
	
	@Autowired
	private UserDAO userdao;
	
	@Test
	@Rollback(false)
	public void testcreateUserData() {
		UserData userdata = new UserData("5","dbngn","cj","User","fdn","abc1@abc.com","null","null","null","Yes");
		UserData saveduserdata = userdao.save(userdata);
		
		assertNotNull(saveduserdata);
	}
	
	@Test
	public void testfindUserDataByUemailAndUpassword() {
		String email = "abc1@abc.com";
		String password = "dbngn";
		Optional<UserData> userdata = userdao.findByUemailAndUpassword(email, password);
		
		assertThat(userdata.get().getUemail()).isEqualTo(email);
		assertThat(userdata.get().getUpassword()).isEqualTo(password);
		
				
	}
	
	@Test
	public void testfindUserDataByUemail() {
		String email = "abc1@abc.com";
		
		Optional<UserData> userdata = userdao.findByUemail(email);
		
		assertThat(userdata.get().getUemail()).isEqualTo(email);
	}
}
