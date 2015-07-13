package com.uestc.testmybatis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uestc.testmybatis.service.MessageService;

@SuppressWarnings("serial")
public class DeleteBatchMessageServlet extends HttpServlet {
	MessageService messageService = new MessageService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] ids = request.getParameterValues("id");

		messageService.deleteBathMessage(ids);
		request.getRequestDispatcher("/list.action").forward(request, response);
	}

}
