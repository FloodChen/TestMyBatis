package com.uestc.testmybatis.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.uestc.testmybatis.entity.MyMessage;

public class MessageDao {
	DBAccess dbAccess = new DBAccess();
	SqlSession sqlSession = null;

	public List<MyMessage> getAllMessage(String command, String description) {
		List<MyMessage> myMessages = new ArrayList<MyMessage>();
		try {
			sqlSession = dbAccess.getSqlSession();
			MyMessage myMessage = new MyMessage();
			myMessage.setCommand(command);
			myMessage.setDescription(description);
			myMessages = sqlSession.selectList("Message.getAllMessage", myMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

		return myMessages;
	}

	public void deleteOneMessage(int id) {
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteOneMessage", id);
			sqlSession.commit();//删除操作要提交事务
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}

	}

	public void deleteBatchMessage(List<Integer> ids) {
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteBatchMessage", ids);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}

	/*传统jdbc处理方式
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
				myMessage.setId(resultSet.getInt("ID"));

				myMessages.add(myMessage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myMessages;
	}

	public boolean deleteOneMessage(int id) {
		boolean b = false;
		StringBuilder sql = new StringBuilder("delete from message where ID=?");
		try {
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setInt(1, id);
			b = statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}*/
}
