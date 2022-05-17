package com.Reservations.Kontroleri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminKontroler {
	
	@RequestMapping(value = "/admin")
	  public String getAdminPage(){
		System.out.println("Admin page was called!");
	      return "adminProfile";
	  }
	
	@RequestMapping(value = "/admin/view-all")
	  public String getEntitiesPage(){
		System.out.println("All entities page was called!");
	      return "adminList";
	  }
	
	@RequestMapping(value = "/admin/my-data")
	  public String getDataPage(){
		System.out.println("My data page was called!");
	      return "adminMyData";
	  }
	
	@RequestMapping(value = "/admin/revenue")
	  public String getRevenuePage(){
		System.out.println("Revenue page was called!");
	      return "adminRevenue";
	  }
	
	@RequestMapping(value = "/admin/reports")
	  public void getReport(){
		System.out.println("Reports were generated!");
	  }
	
	@RequestMapping(value = "/admin/add-admin")
	  public String addAdminForm(){
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
		System.out.println("Revenue was changed!");
	      return "adminRequest";
	  }
}
