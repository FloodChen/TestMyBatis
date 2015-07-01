package com.uestc.testmybatis.service;

import java.util.List;

import com.uestc.testmybatis.dao.MessageDao;
import com.uestc.testmybatis.entity.MyMessage;

public class MessageService {
	private MessageDao messageDao = new MessageDao();

	public List<MyMessage> getAllMessage(String command, String description) {

		return messageDao.getAllMessage(command, description);
	}
}
