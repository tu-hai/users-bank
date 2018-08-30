package com.np.user.bank.dao.repositories;

import com.np.user.bank.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nvtien on 8/28/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
