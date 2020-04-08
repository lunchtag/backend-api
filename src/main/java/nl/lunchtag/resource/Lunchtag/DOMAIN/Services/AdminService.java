package nl.lunchtag.resource.Lunchtag.DOMAIN.Services;

import nl.lunchtag.resource.Lunchtag.DAL.Repository.LunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private LunchRepository lunchRepository;

    @Autowired
    public AdminService(LunchRepository lunchRepository) {
        this.lunchRepository = lunchRepository;
    }

    //-	Als secretaresse wil ik per medewerker kunnen zien wanneer hij/zij heeft mee geluncht zodat ik weet wanneer een persoon heeft mee geluncht.
    //-	Als secretaresse wil ik overzicht per week van hoeveel mensen en wie er hebben mee geluncht zodat dit administratief verwerkt kan worden.
    //-	Als secretaresse wil ik overzicht per maand van hoeveel mensen en wie er hebben mee geluncht zodat dit administratief verwerkt kan worden
    //-	Als secretaresse wil ik per dag personen kunnen toevoegen zodat de administratie kloppend blijft
    //-	Als secretaresse wil ik per dag personen kunnen verwijderen zodat de administratie kloppend blijft

    //-	Als secretaresse wil ik per dag een overzicht van het aantal mensen en wie hiervan hebben mee geluncht zodat dit administratief verwerkt kan worden.
    //-	Als secretaresse wil ik een overzicht per jaar van hoeveel mensen en wie er hebben mee geluncht zodat dit administratief verwerkt kan worden
    //-	Als secretaresse wil ik een overzicht van een persoon kunnen zien zodat ik in kan zien hoe vaak iemand naar de kantine gaat.
}
