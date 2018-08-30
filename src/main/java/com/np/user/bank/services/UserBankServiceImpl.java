package com.np.user.bank.services;

import com.np.user.bank.dao.models.Bank;
import com.np.user.bank.dao.models.User;
import com.np.user.bank.dao.repositories.BankRepository;
import com.np.user.bank.dao.repositories.UserRepository;
import com.np.user.bank.dto.BankDTO;
import com.np.user.bank.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by nvtien on 8/28/18.
 */
@Service
public class UserBankServiceImpl implements UserBankService {

    private UserRepository userRepository;
    private BankRepository bankRepository;

    public UserBankServiceImpl(UserRepository userRepository, BankRepository bankRepository) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getUserId());
            userDTO.setUserName(user.getUserName());
            userDTO.setFullName(user.getFullName());
            userDTO.setAge(user.getAge());
            userDTO.setGender(user.getGender());
            for (Bank bank : user.getBanks()) {
                BankDTO bankDTO = new BankDTO();
                bankDTO.setId(bank.getBankId());
                bankDTO.setName(bank.getBankName());
                bankDTO.setAccount(bank.getBankAccount());
                userDTO.getBanks().add(bankDTO);
            }
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setFullName(userDTO.getFullName());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserName(userDTO.getUserName());
            user.setFullName(userDTO.getFullName());
            user.setAge(userDTO.getAge());
            user.setGender(userDTO.getGender());
            userRepository.save(user);
        }
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<BankDTO> getBanks() {
        List<BankDTO> bankDTOs = new ArrayList<>();
        List<Bank> banks = bankRepository.findAll();
        for (Bank bank : banks) {
            BankDTO bankDTO = new BankDTO();
            bankDTO.setId(bank.getBankId());
            bankDTO.setName(bank.getBankName());
            bankDTO.setAccount(bank.getBankAccount());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(bank.getUser().getUserId());
            userDTO.setUserName(bank.getUser().getUserName());
            userDTO.setFullName(bank.getUser().getFullName());
            userDTO.setAge(bank.getUser().getAge());
            userDTO.setGender(bank.getUser().getGender());

            bankDTO.setUser(userDTO);

            bankDTOs.add(bankDTO);
        }

        return bankDTOs;
    }

    @Override
    public void addBank(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setBankName(bankDTO.getName());
        bank.setBankAccount(bankDTO.getAccount());

        Optional<User> userOptional = userRepository.findById(bankDTO.getUser().getId());

        if (userOptional.isPresent()) {
            bank.setUser(userOptional.get());
            bankRepository.save(bank);
        }
    }

    @Override
    public void updateBank(BankDTO bankDTO) {
        Optional<Bank> bankOptional = bankRepository.findById(bankDTO.getId());
        if (bankOptional.isPresent()) {
            Bank bank = bankOptional.get();
            bank.setBankName(bankDTO.getName());
            bank.setBankAccount(bankDTO.getAccount());

            if (!bank.getUser().getUserId().equals(bankDTO.getUser().getId())) {
                Optional<User> userOptional = userRepository.findById(bankDTO.getUser().getId());
                if (userOptional.isPresent()) {
                    bank.setUser(userOptional.get());
                }
            }
            bankRepository.save(bank);
        }
    }

    @Override
    public void removeBank(Long id) {
        bankRepository.deleteById(id);
    }
}
