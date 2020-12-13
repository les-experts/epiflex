package mappers;

import models.User;

public class UserMapper {
	static UserMapper object = null;

	private UserMapper() {

	}

	public static UserMapper get() {
		if(UserMapper.object == null) {
			UserMapper.object = new UserMapper();
		}
		return UserMapper.object;
	}

	public User authentification(String username, String password_md5) {
		return null;
	}
}
