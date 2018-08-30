package com.np.user.bank.services;

import com.np.user.bank.dto.BankDTO;
import com.np.user.bank.dto.UserDTO;

import java.util.List;

/**
 * Created by nvtien on 8/28/18.
 */
public interface UserBankService {
    List<UserDTO> getUsers();

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void removeUser(Long id);

    List<BankDTO> getBanks();

    void addBank(BankDTO bankDTO);

    void updateBank(BankDTO bankDTO);

    void removeBank(Long id);
}
