package com.uestc.testmybatis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uestc.testmybatis.entity.MyMessage;

public class MessageDao {
	private Connection connection;

	public MessageDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/testmybatis?useUnicode=true&characterEncoding=utf-8", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<MyMessage> getAllMessage(String command, String description) {
		List<MyMessage> myMessages = new ArrayList<MyMessage>(0);
		List<String> params = new ArrayList<String>(0);

		StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from  message where 1=1");
		if (null != command && !"".equals(command.trim())) {
			sql.append(" and COMMAND=?");
			params.add(command);
		}
		if (null != description && !"".equals(description.trim())) {
			sql.append(" and DESCRIPTION like '%' ? '%'");
			params.add(description);
		}

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				preparedStatement.setString(i + 1, params.get(i));
			}

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MyMessage myMessage = new MyMessage();

				myMessage.setCommand(resultSet.getString("COMMAND"));
				myMessage.setContent(resultSet.getString("CONTENT"));
				myMessage.setDescription(resultSet.getString("DESCRIPTION"));
				myMessage.setId("ID");

				myMessages.add(myMessage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myMessages;
	}
}
