package nl.lunchtag.resource.Lunchtag.logic;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.Lunch;
import nl.lunchtag.resource.Lunchtag.models.LunchDTO;
import nl.lunchtag.resource.Lunchtag.service.AccountService;
import nl.lunchtag.resource.Lunchtag.service.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class LunchLogic {

    private final LunchService lunchService;
    private final AccountService accountService;
    private final ExportPdfLogic exportPdfLogic;

    @Autowired
    public LunchLogic(LunchService lunchService, AccountService accountService,ExportPdfLogic exportPdfLogic) {
        this.lunchService = lunchService;
        this.accountService = accountService;
        this.exportPdfLogic = exportPdfLogic;
    }

    public Lunch addLunch(Account account, LunchDTO lunchDTO) {
        // Create new Lunch Instance
        Lunch lunch = new Lunch(lunchDTO.getDate(), account);

        // Add lunch to database
        this.lunchService.createOrUpdate(lunch);

        // Return lunch instance
        return lunch;
    }

    public boolean deleteLunch(UUID id) {
        Optional<Lunch> lunch = this.lunchService.findById(id);

        if(lunch.isPresent()) {
            this.lunchService.delete(lunch.get());
            return true;
        }

        return false;
    }

    public List<Lunch> findAll() {
        return this.lunchService.findAll();
    }

    public List<Lunch> findAllByAccountId(UUID accountId) {
        return this.lunchService.findAllByAccountId(accountId);
    }

    public Optional<Lunch> findById(UUID id) {
        return this.lunchService.findById(id);
    }

    public void generatePdf(int year,int month){
        List<Lunch> lunches = lunchService.findAll();
        List<Lunch> filteredLunches = exportPdfLogic.returnLunchesOfMonth(month,year,lunches);
        Document document = new Document();

        try{
            String path = new File(".").getCanonicalPath() + "/src/main/webapp/WEB-INF/files/";

            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(path+"Maandoverzicht-"+year+"-"+(month+1)+".pdf"));
            document.open();
            Font titleFont=new Font(Font.FontFamily.HELVETICA,25.0f,Font.BOLD, BaseColor.BLACK);
            Font tableFont = new Font(Font.FontFamily.HELVETICA, 18.0f,Font.BOLD, BaseColor.BLACK);
            // Title
            Paragraph title = new Paragraph("Lunchtag - "+year+" Maandnummer " + (month+1),titleFont);
            title.setAlignment(Paragraph.ALIGN_CENTER);

            title.setSpacingAfter(25f);
            document.add(title);

            // Table
            PdfPTable table = new PdfPTable(3);

            // Create cell
            PdfPCell c1 = new PdfPCell(new Paragraph("Voornaam",tableFont));
            PdfPCell c2 = new PdfPCell(new Paragraph("Achternaam",tableFont));
            PdfPCell c3 = new PdfPCell(new Paragraph("Datum",tableFont));
//            PdfPCell c4 = new PdfPCell(new Paragraph("Aantal x gelunched",tableFont));

            // Add cell to table
            table.addCell(c1);
            table.addCell(c2);
            table.addCell(c3);

            for(Lunch lunch : filteredLunches){
                c1= new PdfPCell(new Paragraph(lunch.getAccount().getName()));
                c2= new PdfPCell(new Paragraph(lunch.getAccount().getLastName()));
                c3= new PdfPCell(new Paragraph(lunch.getDate().toString()));

                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
            }

            // Add objects to pdf
            document.add(table);

            Paragraph lunchTitle = new Paragraph("Totaal aantal x meegelunched:" + filteredLunches.size(),titleFont);
            lunchTitle.setAlignment(Paragraph.ALIGN_CENTER);


            document.add(lunchTitle);
            // Close
            document.close();
            writer.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("");
    }
}
