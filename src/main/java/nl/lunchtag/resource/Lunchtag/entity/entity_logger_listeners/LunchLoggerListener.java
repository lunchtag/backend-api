package nl.lunchtag.resource.Lunchtag.entity.entity_logger_listeners;

import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.LogLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

@Service
public class LunchLoggerListener {

    static private LogLogic logLogic;

    @Autowired
    public void init(LogLogic logLogic) {
        LunchLoggerListener.logLogic = logLogic;
    }

    @PrePersist
    public void prePersist(Lunch lunch){
        logLogic.addCreateLog("Lunch added");
    }

    @PreRemove
    public void preDelete(Lunch lunch){
        logLogic.addDeleteLog("Lunch deleted");
    }
}
