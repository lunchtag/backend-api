package nl.lunchtag.resource.Lunchtag.entity.entity_logger_listeners;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.logic.LogLogic;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AccountLoggerListener {

    static private LogLogic logLogic;

    @Autowired
    public void init(LogLogic logLogic) {
        AccountLoggerListener.logLogic = logLogic;
    }

    @PrePersist
    public void prePersist(Account account){
        logLogic.addCreateLogNewAccount(String.format("Account created by %s", account.getName()), account);
    }

    @PreUpdate
    public void preUpdate(Account account){
        logLogic.addUpdateLog(String.format("Account from %s updated", account.getName()));
    }
}
