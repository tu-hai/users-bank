package com.np.user.bank.dao.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nvtien on 8/28/18.
 */
@Entity
@Table(name = "banks")
public class Bank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long bankId;
    
    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "bank_account")
    private String bankAccount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
