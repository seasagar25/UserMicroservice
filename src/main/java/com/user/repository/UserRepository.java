/**
 * 
 */
package com.user.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.user.model.UserEntity;

/**
 * @author sagar
 *
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

}
