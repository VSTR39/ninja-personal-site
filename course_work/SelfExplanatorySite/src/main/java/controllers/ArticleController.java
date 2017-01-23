package controllers;

import javax.inject.Inject;

import dao.AdminDao;
import dao.ArticleDao;
import entities.articles;
import models.AdminPostInformation;
import models.DeletePost;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;

public class ArticleController {

	@Inject
	AdminDao adminDao;
	@Inject
	ArticleDao articleDao;

	public Result writePost(Context context, AdminPostInformation postInformation) {
		Session session = context.getSession();
		String author = session.get("username");
		boolean isWritePostSuccessful = adminDao.isWritePostSuccessful(author, postInformation.getSection(),
				postInformation.getTitle(), postInformation.getContent());
		if (isWritePostSuccessful) {
			context.getFlashScope().success("Post successfully published");
			return Results.redirect("/admin");
		}
		context.getFlashScope().error("Invalid section or invalid title.");
		return Results.redirect("/admin");
	}
	


	public Result deletePost(Context context,DeletePost deletePost){
		boolean isPostDeletedSuccessfully = adminDao.isPostDeletedSuccessfully(deletePost.getTitle());
		if (isPostDeletedSuccessfully) {
			context.getFlashScope().success("Post successfully deleted");
			return Results.redirect("/admin");
		}
		context.getFlashScope().error("Invalid title.");
		return Results.redirect("/admin");
		
	}
}
