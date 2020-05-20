package nl.lunchtag.resource.Lunchtag.service;

import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Log;
import nl.lunchtag.resource.Lunchtag.entity.enums.LogType;
import nl.lunchtag.resource.Lunchtag.respository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void addLog(String logText, Account account, LogType logType){
        Log log = new Log(logText, account, logType);
        logRepository.save(log);
    }
}
