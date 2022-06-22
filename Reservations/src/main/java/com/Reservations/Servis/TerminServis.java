package com.Reservations.Servis;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Reservations.Modeli.Brod;
import com.Reservations.Modeli.Korisnik;
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

	public boolean popraviTerminRezervacije(Rezervacija rez) 
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
				Vikendica vikendica = vikendicaServis.findById(rez.getEntitetId());
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
						boolean pronadjenEntitet = false;
						if(!vikendica.getTerminiZauzetosti().isEmpty())
						{
							
							for(int i = 0; i<vikendica.getTerminiZauzetosti().size();i++)
							{
								if(vikendica.getTerminiZauzetosti().get(i).getID()==terminRezervacije.getID())
								{
									vikendica.getTerminiZauzetosti().set(i, terminRezervacije);
									pronadjenEntitet = true;
									break;
								}
							}
							if(!pronadjenEntitet)vikendica.getTerminiZauzetosti().add(terminRezervacije);
						}
						else vikendica.getTerminiZauzetosti().add(terminRezervacije);
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(rez);
					this.dodijeliVlasnikaIentitet(terminRezervacije, rez);
					vikendica.getTerminiZauzetosti().add(terminRezervacije);
					System.out.println("rez-ter: "+rez.getTermin());
				}
				System.out.println("rezultat: "+terminRezervacije);
				break;
			}
			case brod:
			{
				Brod brod = brodServis.findById(rez.getEntitetId());
				boolean pronadjen = false;
				System.out.println("dobavljam po tipu broda");
				termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.brod);
				System.out.println(termini.size());
				if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
				{
					if(rez.getEntitetId()==termin.getBrod().getID() && termin.getRezervacija()!=null
					&& rez.getID()==termin.getRezervacija().getID() )
					{
						terminRezervacije = termin;
						terminRezervacije.setDatumVremePocetak(rez.getDatum());
						terminRezervacije.izracunajDatumKraja(rez.getTrajanje());
						boolean pronadjenEntitet = false;
						if(!brod.getTerminiZauzetosti().isEmpty())
						{
							
							for(int i = 0; i<brod.getTerminiZauzetosti().size();i++)
							{
								if(brod.getTerminiZauzetosti().get(i).getID()==terminRezervacije.getID())
								{
									brod.getTerminiZauzetosti().set(i, terminRezervacije);
									pronadjenEntitet = true;
									break;
								}
							}
							if(!pronadjenEntitet)brod.getTerminiZauzetosti().add(terminRezervacije);
						}
						else brod.getTerminiZauzetosti().add(terminRezervacije);
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(rez);
					this.dodijeliVlasnikaIentitet(terminRezervacije, rez);
					brod.getTerminiZauzetosti().add(terminRezervacije);
					System.out.println("rez-ter: "+rez.getTermin());
				}
				System.out.println("rezultat: "+terminRezervacije);
				break;
			}
			case usluga:
			{
				Usluga usluga = uslugaServis.findById(rez.getEntitetId());
				boolean pronadjen = false;
				System.out.println("dobavljam po tipu usluge");
				termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.usluga);
				System.out.println(termini.isEmpty());
				if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
				{
					if(rez.getEntitetId()==termin.getUsluga().getID() && termin.getRezervacija()!=null
					&& rez.getID()==termin.getRezervacija().getID())
					{
						terminRezervacije = termin;
						terminRezervacije.setDatumVremePocetak(rez.getDatum());
						terminRezervacije.izracunajDatumKraja(rez.getTrajanje());
						boolean pronadjenEntitet = false;
						if(!usluga.getTerminiZauzetosti().isEmpty())
						{
							
							for(int i = 0; i<usluga.getTerminiZauzetosti().size();i++)
							{
								if(usluga.getTerminiZauzetosti().get(i).getID()==terminRezervacije.getID()
								&& terminRezervacije.getTipEntiteta().equals(TipEntiteta.usluga))
								{
									usluga.getTerminiZauzetosti().set(i, terminRezervacije);
									pronadjenEntitet = true;
									break;
								}
							}
							if(!pronadjenEntitet)usluga.getTerminiZauzetosti().add(terminRezervacije);
						}
						else usluga.getTerminiZauzetosti().add(terminRezervacije);
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(rez);
					this.dodijeliVlasnikaIentitet(terminRezervacije, rez);
					usluga.getTerminiZauzetosti().add(terminRezervacije);
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
	
	public boolean popraviTermineVikendice(Vikendica vikendica, Termin terminVikendice) 
	{
		Termin terminEntiteta = null ;
		List<Termin> termini;
		boolean rezultat = false;
		/*
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.println("Trazim termine za vikendicu: "+vikendica.getID());

		boolean pronadjen = false;
		System.out.println("dobavljam po tipu vikendice");
		termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.vikendica);
		System.out.println(termini.isEmpty());
		if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
		{
			if(termin.getVikendica()!=null && !vikendica.getTerminiZauzetosti().isEmpty() 
				&& vikendica.getID()==termin.getVikendica().getID())
			{
				for(Termin terminZauzetosti : vikendica.getTerminiZauzetosti())
				{
					if(termin.getID()==terminZauzetosti.getID())
					{
					terminZauzetosti.setDatumVremePocetak(terminVikendice.getDatumVremePocetak());
					termin.setDatumVremeKraj(terminZauzetosti.getDatumVremeKraj());
					pronadjen = true;
					}
				}
			}
		}
		if(!pronadjen)
		{
			termin = 
			System.out.println("rezultat: "+terminEntiteta);
			terminEntiteta = new Termin(vikendica);
			this.dodijeliVlasnikaIentitet(terminEntiteta, vikendica);
			System.out.println("rez-ter: "+vikendica.getTermin());
		}
		System.out.println("rezultat: "+terminEntiteta);
		break;
	}
			case 3: //vlasnik broda
			{
				boolean pronadjen = false;
				System.out.println("dobavljam po tipu broda");
				termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.brod);
				if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
				{
					if(vikendica.getEntitetId()==termin.getBrod().getID() && termin.getRezervacija()!=null
					&& vikendica.getID()==termin.getRezervacija().getID())
					{
						terminRezervacije = termin;
						terminRezervacije.setDatumVremePocetak(vikendica.getDatum());
						terminRezervacije.izracunajDatumKraja(vikendica.getTrajanje());
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(vikendica);
					this.dodijeliVlasnikaIentitet(terminRezervacije, vikendica);
					System.out.println("rez-ter: "+vikendica.getTermin());
				}
				System.out.println("rezultat: "+terminRezervacije);
				break;
			}
			case 4: //Instruktor
			{
				boolean pronadjen = false;
				System.out.println("dobavljam po tipu usluge");
				termini = terminRepozitorijum.findByTipEntiteta(TipEntiteta.usluga);
				if(termini!=null && !termini.isEmpty())for(Termin termin : termini)
				{
					if(vikendica.getEntitetId()==termin.getUsluga().getID() && termin.getRezervacija()!=null
					&& vikendica.getID()==termin.getRezervacija().getID())
					{
						terminRezervacije = termin;
						terminRezervacije.setDatumVremePocetak(vikendica.getDatum());
						terminRezervacije.izracunajDatumKraja(vikendica.getTrajanje());
						pronadjen = true;
					}
				}
				if(!pronadjen)
				{
					System.out.println("rezultat: "+terminRezervacije);
					terminRezervacije = new Termin(vikendica);
					this.dodijeliVlasnikaIentitet(terminRezervacije, vikendica);
					System.out.println("rez-ter: "+vikendica.getTermin());
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
		}*/
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
	
	public void obrisiPoID(long id) {
		this.terminRepozitorijum.deleteById(id);
	}
}
