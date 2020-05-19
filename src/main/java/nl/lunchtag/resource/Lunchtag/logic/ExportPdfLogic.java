package nl.lunchtag.resource.Lunchtag.logic;

import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.models.LunchPdfDTO;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import nl.lunchtag.resource.Lunchtag.service.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExportPdfLogic {

    public List<Lunch> returnLunchesOfMonth(int month, int year,List<Lunch> allLunches){
        List<Lunch> filteredLunches = new ArrayList<>();
        List<LunchPdfDTO> lunchPdfDTOS = new ArrayList<>();

        for(Lunch lunch : allLunches){
            // Als de lunchdatum overeenkomt met de datum waarop gefilted wordt, wordt de lunch toegevoegd aan de nieuwe lijst
            Date lunchDate = lunch.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lunchDate);
            int monthNumber = calendar.get(Calendar.MONTH);
            int yearNumber = calendar.get(Calendar.YEAR);

            if(monthNumber == month && yearNumber == year){
                filteredLunches.add(lunch);
            }
        }



        // lijst sorteren
        filteredLunches.sort(Comparator.comparing(l -> l.getDate()));
        System.out.println("");
        return filteredLunches;
    }

    public void uploadFile(){

    }

}
