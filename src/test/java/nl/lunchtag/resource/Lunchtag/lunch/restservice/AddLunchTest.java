package nl.lunchtag.resource.Lunchtag.lunch.restservice;

import nl.lunchtag.resource.Lunchtag.controller.LunchController;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.LunchLogic;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import nl.lunchtag.resource.Lunchtag.service.LunchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddLunchTest {
    @InjectMocks
    private LunchController lunchController;

    @Mock
    private LunchLogic lunchLogic;

    private Account account;
    private LunchDTO lunchDTO;

    @BeforeEach
    void setUp() throws ParseException {
        this.account = new Account(UUID.fromString("123e4567-e89b-42d3-a456-556642440010"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));

        Date NOW = sdf.parse("2020-06-02 00:00:00");

        this.lunchDTO = new LunchDTO();
        this.lunchDTO.setDate(NOW);

        Lunch lunch = new Lunch(new Date(), this.account);
        lunch.setId(UUID.fromString("123e4567-e89b-42d3-a456-556642440000"));

        lenient().when(lunchLogic.addLunch(this.account, lunchDTO)).thenReturn(lunch);
    }

    @Test
    void addLunchWithCorrectLunchDTO() {
        HttpStatus status = this.lunchController.addLunch(this.account, this.lunchDTO).getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void addLunchWithWithNoDate() {
        HttpStatus status = this.lunchController.addLunch(this.account, new LunchDTO()).getStatusCode();
        assertEquals(400, status.value());
    }
}