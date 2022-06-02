package com.Reservations.Servis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Reservations.DTO.SlikaDTO;
import com.Reservations.Repozitorijumi.SlikaRepozitorijum;

@Service
public class SnimanjeDatotekaServis 
{
	
	@Autowired
	SlikaRepozitorijum slikaRepozitorijum;
	
	public final String putanjaSlika = "/img/";
	public static String snimiDatoteku(String putanja, String nazivDatoteke, MultipartFile datoteka) throws IOException
	{
		
		return nazivDatoteke;
		/*Path putanjaSnimanja = Paths.get(putanja);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }*/      
	}

	public boolean snimiDatoteku(SlikaDTO slikaDTO) throws IOException
	{
		if(slikaDTO.getPutanja()==null) System.out.println("Putanja je null");
	    if(slikaDTO.getNazivSlike()==null) System.out.println("Naziv je null");
	    if(slikaDTO.getSlika()!=null) System.out.println("Slika ne postoji");
		File slika = new File(this.putanjaSlika+slikaDTO.getNazivSlike());
		System.out.println("Usao u snimi");
		try(OutputStream os = new FileOutputStream(slika))
		{
			os.write(slikaDTO.getSlika().getBytes());
			os.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
