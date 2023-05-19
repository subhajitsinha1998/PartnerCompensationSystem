package com.pcs.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pcs.user.dao.UserRepository;
import com.pcs.user.exception.CustomException;
import com.pcs.user.model.User;
import com.pcs.user.service.UserService;

@SpringBootTest
class UserApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	/*
	 * Test adding a new user successfully
	 */
	@Test
	public void createUserTest1() {
		User user = new User();
		user.setEmployeeId(1);
		user.setEmail("test@email.test");
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
		assertEquals(1, userService.createUser(user).getEmployeeId());
	}

	/*
	 * Test adding a new user unsuccessful
	 */
	@Test
	public void createUserTest2() {
		User user = new User();
		user.setEmployeeId(1);
		user.setEmail("test@email.test");
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.existsByEmail(anyString())).thenReturn(true);
		assertThrows(
				CustomException.class,
				() -> userService.createUser(user));
	}

	/*
	 * Updating an user successfully
	 */
	@Test
	public void updateUserTest1() {
		User user = new User();
		user.setEmployeeId(1);
		user.setEmail("test@email.test");
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
		assertEquals(1, userService.updateUser(1, user).getEmployeeId());
	}

	/*
	 * Test adding a new user which does not exist
	 */
	@Test
	public void updateUserTest2() {
		User user = new User();
		user.setEmployeeId(1);
		user.setEmail("test@email.test");
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.findById(anyInt())).thenThrow(CustomException.class);
		assertThrows(
				CustomException.class,
				() -> userService.updateUser(1, user));
	}

	/*
	 * Test updating email of existing user
	 */
	@Test
	public void updateUserTest3() {
		User user = new User();
		user.setEmployeeId(1);
		user.setEmail("test@email.test");
		User updatedUser = user;
		updatedUser.setEmail("update@email.test");
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.existsById(anyInt())).thenReturn(true);
		assertThrows(
				CustomException.class,
				() -> userService.updateUser(1, user));
	}

}
