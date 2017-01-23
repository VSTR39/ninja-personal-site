/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;
import controllers.ArticleController;
import controllers.LoginLogoutController;
import controllers.PasswordRestoreController;
import controllers.RegistrationController;

public class Routes implements ApplicationRoutes {

	@Override
	public void init(Router router) {

		router.GET().route("/").with(ApplicationController.class, "welcome");
		router.GET().route("/Website").with(ApplicationController.class, "Website");
		router.GET().route("/allPosts").with(ApplicationController.class, "allPosts");
		router.GET().route("/admin").with(ApplicationController.class, "admin");
		router.GET().route("/about_me").with(ApplicationController.class, "about_me");
		router.GET().route("/java").with(ApplicationController.class, "java");
		router.GET().route("/ninja").with(ApplicationController.class, "ninja");
		router.GET().route("/python").with(ApplicationController.class, "python");
		router.GET().route("/c_plus_plus").with(ApplicationController.class, "c_plus_plus");
		router.GET().route("/c_sharp").with(ApplicationController.class, "c_sharp");
		router.GET().route("/contacts").with(ApplicationController.class, "contacts");
		router.GET().route("/logout").with(LoginLogoutController.class, "logout");
		router.GET().route("/password_restore").with(ApplicationController.class, "password_restore");
		router.GET().route("/admin_post").with(ApplicationController.class, "admin_post");
		router.GET().route("/registration").with(ApplicationController.class, "registration");
		router.GET().route("/all_users").with(ApplicationController.class, "all_users");
		router.GET().route("/article/{id}").with(ApplicationController.class, "articleShow");
		router.GET().route("/user/{id}").with(ApplicationController.class, "userShow");
		router.GET().route("/deleteUser").with(ApplicationController.class, "deleteUser");
		router.GET().route("/grantAdminship").with(ApplicationController.class, "grantAdminship");
		router.GET().route("/deletePost").with(ApplicationController.class,"deletePost");

		router.POST().route("/admin_post").with(ArticleController.class, "writePost");
		router.POST().route("/deletePost").with(ArticleController.class, "deletePost");
		router.POST().route("/").with(LoginLogoutController.class, "performLogin");
		router.POST().route("/registration").with(RegistrationController.class, "register");
		router.POST().route("/password_restore").with(PasswordRestoreController.class, "restorePassword");
		router.POST().route("/deleteUser").with(ApplicationController.class, "delete");
		router.POST().route("/grantAdminship").with(ApplicationController.class, "grant");;
		// router.GET().route("/registration_sucessful").with(RegistrationController.class,"register");
		///////////////////////////////////////////////////////////////////////
		// Assets (pictures / javascript)
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
		router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
		///////////////////////////////////////////////////////////////////////
		// Index / Catchall shows index page
		///////////////////////////////////////////////////////////////////////
		// router.GET().route("/.*").with(ApplicationController.class, "index");
	}

}
