package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Log;
import nl.lunchtag.resource.Lunchtag.entity.enums.LogType;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import nl.lunchtag.resource.Lunchtag.service.LogService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LogLogic {

    LogService logService;
    AccountService accountService;

    @Autowired
    public LogLogic(LogService logService, AccountService accountService) {
        this.logService = logService;
        this.accountService = accountService;
    }

    public void addCreateLogNewAccount(String text, Account account){
        logService.addLog(Jsoup.parse(text).text(), account, LogType.CREATE);
    }

    public void addDeleteLog(String text){
        UUID id = ((Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Account account = accountService.getUserById(id);
        logService.addLog(Jsoup.parse(text).text(), account, LogType.DELETE);
    }

    public void addCreateLog(String text){
        UUID id = ((Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Account account = accountService.getUserById(id);
        logService.addLog(Jsoup.parse(text).text(), account, LogType.CREATE);
    }

    public void addUpdateLog(String text){
        UUID id = ((Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Account account = accountService.getUserById(id);
        logService.addLog(Jsoup.parse(text).text(), account, LogType.UPDATE);
    }

    public List<Log> getAllLogs(){
        return logService.getAllLogs();
    }
}
