

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.dao.UserD;
import com.alibaba.model.User;

@Repository("userDao")
public class UserDao  {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public User findUserByName(String username)
	{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//User user = sqlSession.selectOne("com.alibaba.dao.UserMapper.findUserByName", username);
		UserD ud = sqlSession.getMapper(UserD.class);
		User user = ud.findUserByName(username);
		sqlSession.close();
		return user;
	}
	
	
}
