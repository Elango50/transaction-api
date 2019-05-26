/**
 * 
 */
package com.revolut.transaction.api.dao;

import java.util.List;

import com.revolut.transaction.api.exception.TransactionException;
import com.revolut.transaction.api.model.User;

/**
 * @author Elangovan
 *
 */
public interface UserDAO {

	List<User> getAllUsers() throws TransactionException;

	User getUserById(long userId) throws TransactionException;

	User getUserByName(String userName) throws TransactionException;

	/**
	 * @param user: user to be created
	 * @return userId generated from insertion. return -1 on error
	 */
	long insertUser(User user) throws TransactionException;

	int updateUser(Long userId, User user) throws TransactionException;

	int deleteUser(long userId) throws TransactionException;
}
