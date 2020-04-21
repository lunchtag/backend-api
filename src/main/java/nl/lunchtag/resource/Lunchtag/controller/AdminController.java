package nl.lunchtag.resource.Lunchtag.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.lunchtag.resource.Lunchtag.controller.enums.LunchResponse;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.AdminLogic;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Admin")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private AdminLogic adminLogic;

    @Autowired
    public AdminController(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }
    @ApiOperation(value = "AddLunch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK!, Succesfull")
    })
    @PostMapping(value = "/lunch")
    public ResponseEntity addLunch(@AuthenticationPrincipal Account account, @RequestBody LunchDTO lunchDTO) {
        Lunch lunch = this.adminLogic.addLunch(account, lunchDTO);

        if(lunch != null) {
            return ResponseEntity.ok(lunch);
        }

        return new ResponseEntity<>(LunchResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
    }

}
