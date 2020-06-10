package nl.lunchtag.resource.Lunchtag.lunch.restservice;

import nl.lunchtag.resource.Lunchtag.controller.LunchController;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.logic.LunchLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class GetLunchTest {
    @InjectMocks
    private LunchController lunchController;

    @Mock
    private LunchLogic lunchLogic;

    @BeforeEach
    void setUp() {
        Account account = new Account(UUID.fromString("123e4567-e89b-42d3-a456-556642440010"));

        Lunch lunch = new Lunch(new Date(), account);
        lunch.setId(UUID.fromString("123e4567-e89b-42d3-a456-556642440000"));

        List<Lunch> lunches = new ArrayList<>();
        lunches.add(lunch);

        lenient().when(lunchLogic.findById(lunch.getId())).thenReturn(Optional.of(lunch));
        lenient().when(lunchLogic.findAllByAccountId(account.getId())).thenReturn(lunches);
        lenient().when(lunchLogic.findAll()).thenReturn(lunches);
    }

    @Test
    void findLunchByCorrectLunchId() {
        HttpStatus status = lunchController.getLunch("123e4567-e89b-42d3-a456-556642440000").getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void findLunchByIncorrectLunchId() {
        HttpStatus status = lunchController.getLunch("123e4567-e89b-42d3-a456-556642440001").getStatusCode();
        assertEquals(400, status.value());
    }

    @Test
    void findAllLunches() {
        HttpStatus status = lunchController.getGlobalLunches().getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void findAllLunchedByCorrectAccountId() {
        HttpStatus status = lunchController.getLunchesByAccount("123e4567-e89b-42d3-a456-556642440010").getStatusCode();
        assertEquals(200, status.value());
    }

    @Test
    void findAllLunchesByIncorrectAccountId() {
        HttpStatus status = lunchController.getLunchesByAccount("123e4203-e89b-42d3-a456-556642440010").getStatusCode();
        assertEquals(400, status.value());
    }
}