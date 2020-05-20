package nl.lunchtag.resource.Lunchtag.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nl.lunchtag.resource.Lunchtag.controller.enums.LunchResponse;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.ExportPdfLogic;
import nl.lunchtag.resource.Lunchtag.logic.LunchLogic;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Api(tags = "Accounts")
@RequestMapping(value = "/lunch")
@RestController
public class LunchController {
        private Logger logger = LoggerFactory.getLogger(LunchController.class);

        private LunchLogic lunchLogic;

        // Add empty constructor for testing
        public LunchController() {}

        @Autowired
        public LunchController(LunchLogic lunchLogic) {
            this.lunchLogic = lunchLogic;
        }

        @ApiOperation(value = "AddLunch")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "OK!, Succesfull")
        })
        @PostMapping
        public ResponseEntity addLunch(@AuthenticationPrincipal Account account, @RequestBody LunchDTO lunchDTO) {
            Lunch lunch = this.lunchLogic.addLunch(account, lunchDTO);

            if(lunch != null) {
                return ResponseEntity.ok(lunch);
            }

            return new ResponseEntity<>(LunchResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @ApiOperation(value = "getLunchesByAccountId")
        @GetMapping("/account/{accountId}")
        public ResponseEntity getLunchesByAccount(@PathVariable String accountId) {
            List<Lunch> lunches = this.lunchLogic.findAllByAccountId(UUID.fromString(accountId));

            if(!lunches.isEmpty()) {
                return ResponseEntity.ok(lunches);
            }

            return new ResponseEntity<>(LunchResponse.NO_LUNCHES.toString(), HttpStatus.BAD_REQUEST);
        }

        @ApiOperation(value = "getLunch")
        @GetMapping("/{lunchId}")
        public ResponseEntity getLunch(@PathVariable String lunchId) {
            Optional<Lunch> lunch = this.lunchLogic.findById(UUID.fromString(lunchId));

            if(lunch.isPresent()) {
                return ResponseEntity.ok(lunch.get());
            }

            return new ResponseEntity<>(LunchResponse.NO_LUNCH.toString(), HttpStatus.BAD_REQUEST);
        }

        @ApiOperation(value = "GetAllLunches")
        @GetMapping
        public ResponseEntity getAllLunches(@AuthenticationPrincipal Account account) {
            List<Lunch> lunches = this.lunchLogic.findAllByAccountId(account.getId());

            if(!lunches.isEmpty()) {
                return ResponseEntity.ok(lunches);
            }

            return new ResponseEntity<>(LunchResponse.NO_LUNCHES.toString(), HttpStatus.BAD_REQUEST);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @ApiOperation(value = "getGlobalLunches")
        @GetMapping("/all")
        public ResponseEntity getGlobalLunches() {
            List<Lunch> lunches = this.lunchLogic.findAll();

            if(!lunches.isEmpty()) {
                return ResponseEntity.ok(lunches);
            }

            return new ResponseEntity<>(LunchResponse.NO_LUNCHES.toString(), HttpStatus.BAD_REQUEST);
        }

        @ApiOperation(value = "RemoveLunch")
        @DeleteMapping("/{lunchId}")
        public ResponseEntity removeLunch(@AuthenticationPrincipal Account account, @PathVariable String lunchId) {
            try {
                if (lunchLogic.deleteLunch(UUID.fromString(lunchId))) {
                    return ResponseEntity.ok("Deleted");
                }
            } catch(Exception e) {
                logger.error("User {} gave error {}", account.getEmail(), e);
                return new ResponseEntity<>(LunchResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(LunchResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        @ApiOperation(value = "GetLunchOverviewMonth")
        @GetMapping("/export/{year}/{month}")
        public ResponseEntity getLunchOverviewMonth(@PathVariable String year, @PathVariable String month) {
            int monthNumber = Integer.parseInt(month);
            monthNumber++;
            try{
                this.lunchLogic.generatePdf(Integer.parseInt(year),Integer.parseInt(month));
                return ResponseEntity.ok("/files/Maandoverzicht/"+year+"/"+monthNumber);
            }catch(Exception e){
                return new ResponseEntity<>(LunchResponse.NO_LUNCHES.toString(), HttpStatus.BAD_REQUEST);
            }


        }
}
