package com.smartuserplatform.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.smartuserplatform.model.User;

@Repository
public class UserRepository {
	
	private Map<Long, User> store = new HashMap<Long, User>();
	
	public void saveUser(User user) {
		store.put(user.getId(), user);
	}
	
	public User getUser(Long id) {
		return store.get(id);
	}
	
	public Collection<User> retriveUsersList(){
		return store.values();
	}
	
	public int storeSize() {
		return store.size();
	}

}
