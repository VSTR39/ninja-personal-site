package models;

import ninja.jpa.UnitOfWork;

@UnitOfWork
public class DeleteUser {
String userId;

public String getUsername() {
	return userId;
}

public void setUsername(String username) {
	this.userId = username;
}
}
