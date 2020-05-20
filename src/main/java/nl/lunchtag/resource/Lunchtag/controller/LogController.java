package nl.lunchtag.resource.Lunchtag.controller;

import nl.lunchtag.resource.Lunchtag.controller.enums.LogResponse;
import nl.lunchtag.resource.Lunchtag.logic.LogLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

    private final LogLogic logLogic;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LogController(LogLogic logLogic) {
        this.logLogic = logLogic;
    }

    @GetMapping
    public ResponseEntity getAllLogs(){
        try {
            return ResponseEntity.ok(logLogic.getAllLogs());
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(LogResponse.NO_LOGS);
        }
    }
}
