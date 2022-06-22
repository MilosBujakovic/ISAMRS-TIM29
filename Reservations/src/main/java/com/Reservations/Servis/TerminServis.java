package com.Reservations.Servis;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Rezervacija;
import com.Reservations.Modeli.Termin;
import com.Reservations.Modeli.Usluga;
import com.Reservations.Modeli.Vikendica;
import com.Reservations.Modeli.enums.TipEntiteta;
import com.Reservations.Repozitorijumi.TerminRepozitorijum;

@Service
public class TerminServis 
{
	@Autowired
	TerminRepozitorijum terminRepozitorijum;
	
	@Autowired
	VikendicaServis vikendicaServis;
	
	@Autowired
	UslugaServis uslugaServis;
	
	@Autowired
	BrodServis brodServis;
	
	@Autowired
	KorisnikServis korisnikServis;

	public boolean popraviTermin(Rezervacija rez) 
	{
		Termin terminRezervacije = null ;
		List<Termin> termini;
		boolean rezultat = false;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.println("Trazim termine za rez: "+rez.getID());
		switch(rez.getTipEntiteta())
		{
			case vikendica:
			{
				boolean pronadjen = false;
				System.out.println("dobavljam po tipu vikendice");
				termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.vikendica);
				System.out.println(termini.isEmpty());
				if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
				{
					if(rez.getEntitetId()==termin.getVikendica().getID() && termin.getRezervacija()!=null
					&& rez.getID()==termin.getRezervacija().getID())
					{
						terminRezervacije = termin;
						terminRezervacije.setDatumVremePocetak(rez.getDatum());
						terminRezervacije.izracunajDatumKraja(rez.getTrajanje());
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(rez);
					this.dodijeliVlasnikaIentitet(terminRezervacije, rez);
					System.out.println("rez-ter: "+rez.getTermin());
				}
				System.out.println("rezultat: "+terminRezervacije);
				break;
			}
			case brod:
			{
				boolean pronadjen = false;
				System.out.println("dobavljam po tipu broda");
				termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.brod);
				if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
				{
					if(rez.getEntitetId()==termin.getBrod().getID() && termin.getRezervacija()!=null
					&& rez.getID()==termin.getRezervacija().getID())
					{
						terminRezervacije = termin;
						terminRezervacije.setDatumVremePocetak(rez.getDatum());
						terminRezervacije.izracunajDatumKraja(rez.getTrajanje());
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(rez);
					this.dodijeliVlasnikaIentitet(terminRezervacije, rez);
					System.out.println("rez-ter: "+rez.getTermin());
				}
				System.out.println("rezultat: "+terminRezervacije);
				break;
			}
			case usluga:
			{
				boolean pronadjen = false;
				System.out.println("dobavljam po tipu usluge");
				termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.usluga);
				if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
				{
					if(rez.getEntitetId()==termin.getUsluga().getID() && termin.getRezervacija()!=null
					&& rez.getID()==termin.getRezervacija().getID())
					{
						terminRezervacije = termin;
						terminRezervacije.setDatumVremePocetak(rez.getDatum());
						terminRezervacije.izracunajDatumKraja(rez.getTrajanje());
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(rez);
					this.dodijeliVlasnikaIentitet(terminRezervacije, rez);
					System.out.println("rez-ter: "+rez.getTermin());
				}
				System.out.println("rezultat: "+terminRezervacije);
				break;
			}
			
		}
		if(terminRezervacije!=null)
		{
			rezultat = true;
			System.out.println("rezulzat je: "+rezultat);
			terminRepozitorijum.save(terminRezervacije);
		}
		return rezultat;
	}
	
	public void dodijeliVlasnikaIentitet(Termin termin, Rezervacija rez)
	{
		try
		{
			if(termin.getTipEntiteta().equals(TipEntiteta.vikendica) )
			{
				Vikendica vik = vikendicaServis.findById(rez.getEntitetId());
				termin.setVikendica(vik );
				termin.setVlasnik( termin.getVikendica().getVlasnik() );
			}
			else if(termin.getTipEntiteta().equals(TipEntiteta.brod) )
			{
				Brod brod = brodServis.findById(rez.getEntitetId() );
				termin.setBrod ( brod );
				termin.setVlasnik( termin.getBrod().getVlasnik() );
			}
			else //if(termin.getTipEntiteta().equals(TipEntiteta.usluga))
			{
				Usluga usluga = uslugaServis.findById(rez.getEntitetId() );
				termin.setUsluga( usluga );
				termin.setVlasnik( termin.getUsluga().getInstruktor() );
			}
			rez.setTermin(termin);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
