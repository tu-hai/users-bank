package com.np.user.bank.dao.repositories;

import com.np.user.bank.dao.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nvtien on 8/28/18.
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
