package com.uestc.testmybatis.service;

import java.util.ArrayList;
import java.util.List;

import com.uestc.testmybatis.dao.MessageDao;
import com.uestc.testmybatis.entity.MyMessage;

public class MessageService {
	private MessageDao messageDao = new MessageDao();

	public List<MyMessage> getAllMessage(String command, String description) {

		return messageDao.getAllMessage(command, description);
	}

	public void deleteOneMessage(String id) {
		if (null != id && !"".equals(id.trim())) {
			messageDao.deleteOneMessage(Integer.parseInt(id));
		}
	}

	public void deleteBathMessage(String[] ids) {
		List<Integer> idList = new ArrayList<Integer>(0);
		for (String id : ids) {
			idList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatchMessage(idList);
	}
}
