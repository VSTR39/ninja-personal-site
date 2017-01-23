/**
 * Copyright (C) 2013 the original author or authors.
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

package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.*;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.common.collect.Maps;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import dao.AdminDao;
import dao.ArticleDao;
import dao.UserDao;
import entities.articles;
import entities.registered_users;
import models.ArticleComment;
import models.DeleteUser;
import models.GrantAdminship;
import models.LoginInformation;

@Singleton
public class ApplicationController {
	@Inject
	ArticleDao articleDao;
	@Inject
	UserDao userDao;
	@Inject
	AdminDao adminDao;

	public Result welcome(Context context) {
		Session session = context.getSession();
		session.clear();
		return Results.html();
	}

	public Result admin_post() {
		return Results.html();

	}

	public Result admin() {
		return Results.html();
	}
	
	public Result java(){
		List<articles> posts = (List<articles>) articleDao.getAllPostsInSection(1);
		Map<String, Object> map = Maps.newHashMap();
		map.put("posts", posts);
		return Results.html().render(map);
	}
	public Result ninja(){
		List<articles> posts = (List<articles>) articleDao.getAllPostsInSection(2);
		Map<String, Object> map = Maps.newHashMap();
		map.put("posts", posts);
		return Results.html().render(map);
	}
	public Result python(){
		List<articles> posts = (List<articles>) articleDao.getAllPostsInSection(3);
		Map<String, Object> map = Maps.newHashMap();
		map.put("posts", posts);
		return Results.html().render(map);
	}
	public Result c_plus_plus(){
		List<articles> posts = (List<articles>) articleDao.getAllPostsInSection(4);
		Map<String, Object> map = Maps.newHashMap();
		map.put("posts", posts);
		return Results.html().render(map);
	}
	public Result c_sharp(){
		List<articles> posts = (List<articles>) articleDao.getAllPostsInSection(5);
		Map<String, Object> map = Maps.newHashMap();
		map.put("posts", posts);
		return Results.html().render(map);
	}
	
	public Result Website() {
		List<articles> posts = (List<articles>) articleDao.getAllArticles();
		Map<String, Object> map = Maps.newHashMap();
		map.put("posts", posts);
		return Results.html().render(map);
	}

	public Result allPosts() {
		List<articles> posts = (List<articles>) articleDao.getAllArticles();
		Map<String, Object> map = Maps.newHashMap();
		map.put("posts", posts);
		return Results.html().render(map);
	}
	
	public Result deletePost() {
		return Results.html();
	}
	
	public Result registration() {
		return Results.html();
	}

	public Result password_restore() {
		return Results.html();
	}

	public Result articleShow(@PathParam("id") Long id) {

		articles article = null;
		List comments = null;
		if (id != null) {

			article = articleDao.getArticle(id);
	
		}
		
		return Results.html().render("article", article);
				

	}
	
	public Result about_me(){
		return Results.html();
	}
	
	public Result contacts(){
		return Results.html();
	}

	public Result all_users() {
		List<registered_users> users = userDao.getAllUsers();
		Map<String, Object> map = Maps.newHashMap();
		map.put("users", users);
		return Results.html().render(map);
	}

	public Result userShow(@PathParam("id") Long id) {
		registered_users user = null;
		if (id != null) {
			user = userDao.getUser(id);
		}
		return Results.html().render("user", user);
	}

	public Result delete(Context context, DeleteUser userId) {
		boolean isDeleteUserSuccessful = userDao.isDeleteUserSuccessful(userId.getUsername());
		if (isDeleteUserSuccessful) {
			context.getFlashScope().success("User successfully deleted");
			return Results.redirect("/admin");
		}
		context.getFlashScope().error("No such user or could not delete user");
		return Results.redirect("/admin");
	}

	public Result deleteUser() {
		return Results.html();
	}

	public Result grantAdminship() {
		return Results.html();
	}

	public Result grant(Context context, GrantAdminship grantAdminship) {
		boolean isSuccesfullyGrantedAdminship = adminDao.isSuccessfullyGrantedAdminship(grantAdminship.getUsername());
		if (isSuccesfullyGrantedAdminship) {
			context.getFlashScope().success("User granted adminship");
			return Results.redirect("/admin");
		}
		context.getFlashScope().error("No such user or already an admin");
		return Results.redirect("/admin");
	}

}
