package controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.Provider;

import dao.AdminDao;
import dao.UserDao;
import models.LoginInformation;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

public class LoginLogoutController {
	@Inject
	UserDao loginDao;
	@Inject
	AdminDao adminDao;

	public Result performLogin(Context context, LoginInformation login) {
		boolean isValidLogin = loginDao.isUsernameAndPasswordValid(login.getUsername(), login.getPassword());
		boolean isAdmin = adminDao.isAdmin(login.getUsername());
		if (isValidLogin && isAdmin) {
			Session session = context.getSession();
			session.put("username", login.getUsername());
			context.getFlashScope().success("Hello," + login.getUsername());
			return Results.redirect("/admin");
		} else if (isValidLogin) {
			Session session = context.getSession();
			session.put("username", login.getUsername());

			context.getFlashScope().success("Hello," + login.getUsername());
			return Results.redirect("/Website");
		}
		context.getFlashScope().put("username", login.getUsername());
		context.getFlashScope().error("Wrong username or password.");
		return Results.redirect("/");
	}

	public Result logout(Context context) {

		// remove any user dependent information
		context.getSessionCookie().clear();
		context.getFlashCookie().success("login.logoutSuccessful");

		return Results.redirect("/");

	}
}
