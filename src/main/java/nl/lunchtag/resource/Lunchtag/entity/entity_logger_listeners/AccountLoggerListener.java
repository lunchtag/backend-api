package nl.lunchtag.resource.Lunchtag.entity.entity_logger_listeners;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.logic.LogLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Service
public class AccountLoggerListener {

    static private LogLogic logLogic;

    @Autowired
    public void init(LogLogic logLogic) {
        AccountLoggerListener.logLogic = logLogic;
    }

    @PrePersist
    public void prePersist(Account account){
        logLogic.addCreateLogNewAccount(String.format("Account created for %s %s ", account.getName(), account.getLastName()), account);
    }

    @PreUpdate
    public void preUpdate(Account account){
        logLogic.addUpdateLog(String.format("Account for %s %s updated", account.getName(), account.getLastName()));
    }
}
