package com.uestc.testmybatis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uestc.testmybatis.service.MessageService;

@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MessageService messageService = new MessageService();
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		//表单回显
		request.setAttribute("command", command);
		request.setAttribute("description", description);
		request.setAttribute("messageList", messageService.getAllMessage(command, description));
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}
}
