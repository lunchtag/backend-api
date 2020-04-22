package nl.lunchtag.resource.Lunchtag.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.lunchtag.resource.Lunchtag.controller.enums.AdminResponse;
import nl.lunchtag.resource.Lunchtag.controller.enums.LunchResponse;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.AdminLogic;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Admin")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private AdminLogic adminLogic;

    @Autowired
    public AdminController(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }
    @ApiOperation(value = "AddLunchAdmin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK!, Succesfull")
    })
    @PostMapping(value = "/lunch/{accountId}")
    public ResponseEntity addLunch(@AuthenticationPrincipal Account account, @RequestBody LunchDTO lunchDTO, @PathVariable String accountId) {
        Lunch lunch = this.adminLogic.addLunch(accountId, lunchDTO);

        if(lunch != null) {
            return ResponseEntity.ok(lunch);
        }

        return new ResponseEntity<>(LunchResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "DeleteLunchAdmin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK!, Succesfull")
    })
    @DeleteMapping(value = "/lunch/{accountId}/{lunchId}")
    public ResponseEntity deleteLunch(@AuthenticationPrincipal Account account, @PathVariable String accountId, @PathVariable String lunchId){
        switch(this.adminLogic.deleteLunch(lunchId)) {
            case SUCCESS: return ResponseEntity.ok("Succesfully deleted");
            case NO_LUNCH: return new ResponseEntity<>(AdminResponse.NO_LUNCH.toString(), HttpStatus.NOT_FOUND);
            default: return new ResponseEntity<>(AdminResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }
    }

}
