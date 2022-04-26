package tpv.bros.common.dao;

import org.springframework.stereotype.Repository;

import tpv.bros.common.table.User;
import tpv.core.database.Database;

@Repository
public class UserDao {
	public void insert(User user) {
		Database.insert(user);
	}
}
