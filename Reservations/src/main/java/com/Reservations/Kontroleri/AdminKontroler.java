package com.Reservations.Kontroleri;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Reservations.Dodaci.PrihodPDFGenerator;
import com.Reservations.Modeli.Prihod;
import com.Reservations.Servis.PrihodServis;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.lowagie.text.DocumentException;

@Controller
public class AdminKontroler {
	
	@Autowired
	PrihodServis prihodServis;
	
	@RequestMapping(value = "/admin")
	  public String getAdminPage(){
		System.out.println("Admin page was called!");
	      return "adminProfile";
	  }
	
	@RequestMapping(value = "/admin/view-all", method = RequestMethod.GET)
	  public String getEntitiesPage(){
		System.out.println("All entities page was called!");
	    return "adminList";
	  }
	
	@RequestMapping(value = "/admin/my-profile")
	  public String getDataPage(){
		System.out.println("My profile page was called!");
	      return "adminMyData";
	  }
	
	@RequestMapping(value = "/admin/revenue")
	  public String getRevenuePage(){
		System.out.println("Revenue page was called!");
	      return "adminRevenue";
	  }
	
	@GetMapping("/admin/reports")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=prihodi_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Prihod> lista = prihodServis.listAll();
         
        PrihodPDFGenerator pdf = new PrihodPDFGenerator(lista);
        pdf.export(response);
         
    }
	
	@RequestMapping(value = "/admin/add-admin")
	  public String addAdminForm(Model model){
		System.out.println("Admin form was called!");
	      return "adminAddNew";
	  }
	
	@RequestMapping(value = "/admin/change-revenue-percent")
	  public String changeRevenue(){
		System.out.println("Revenue was changed!");
	      return "adminRevenue";
	  }
	
	@RequestMapping(value = "/admin/requests/{id}/submit")
	  public String informSeller(){
		System.out.println("Notification was sent!");
	      return "adminRequest";
	  }
	
}
