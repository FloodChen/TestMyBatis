package com.uestc.testmybatis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uestc.testmybatis.service.MessageService;

@SuppressWarnings("serial")
public class DeleteOneMessageServlet extends HttpServlet {
	MessageService messageService = new MessageService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		messageService.deleteOneMessage(request.getParameter("id"));
		request.getRequestDispatcher("/list.action").forward(request, response);
	}

}
