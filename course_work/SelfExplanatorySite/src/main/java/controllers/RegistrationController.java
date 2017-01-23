package controllers;

import javax.inject.Inject;

import dao.RegisterDao;
import models.RegisterInformation;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;

public class RegistrationController {
	@Inject
	RegisterDao registrationDao;

	public Result register(Context context, RegisterInformation registration) {
		boolean isRegistrationValid = registrationDao.isRegistrationSuccessful(registration.getUsername(),
				registration.getPassword(), registration.getEmail());
		if (isRegistrationValid) {
			Session session = context.getSession();
			session.put("username", registration.getUsername());
			context.getFlashScope().success("Hello," + registration.getUsername());
			return Results.redirect("/Website");
		}
		context.getFlashScope().put("username", registration.getUsername());
		context.getFlashScope().error("Username taken.");
		return Results.redirect("/registration");
	}

}
