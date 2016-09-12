package com.goeurotest.engine.persistance;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import com.goeurotest.bo.City;


/**
 * Receives a list of cities and generates a CSV file with them using apache commons CSV
 * CSV_HEADER = {"id","name","type","latitude","longitude"}
 * CSV_FILE_NAME = goEuroCities.csv
 * 
 * When one field has no value, an empty string will be added for that cell.
 * When there are no cities or the list is null, it will print a file with only headers.
 * 
 * This utility always generates the same file, so previous files will be overriden. 
 * This is only to make it simpler, a real service will generate a unique name based on a agreed criteria: UUID, or timestamp.
 * 
 * @author ana
 *
 */
@Service
public class CsvPersistanceServiceImpl implements PersistanceService {

	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final Object[] FILE_HEADER = {"id","name","type","latitude","longitude"};
	private static final String FILE_NAME = "goEuroCities.csv";
	
	@Override
	public boolean persist(List<City> cities) throws IOException {
		
		FileWriter fileWriter = null;
		CSVPrinter csvPrinter = null;
		
		try{
			//initialize the file
			fileWriter = new FileWriter(FILE_NAME);
			//initialize the printer object
			csvPrinter = new CSVPrinter(fileWriter,CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR));
			
			//create the file header
			csvPrinter.printRecord(FILE_HEADER);
			
			if(cities != null){
				for (City city: cities) {
					csvPrinter.printRecord(convertToCsvLine(city));
				}
			}
			return true;
			
		} finally {
			//closing streams
			fileWriter.flush();
			fileWriter.close();
			csvPrinter.close();
		}
	}
	
	/**
	 * Transforms a city object into the needed structure for the CSV line.
	 * Format: {"id","name","type","latitude","longitude"}
	 * If one field is null or empty, it will return an empty string
	 * 
	 * @param city
	 * @return list of strings with the fields to be added to the CSV File
	 */
	private List<String> convertToCsvLine(City city){
		
		List<String> cityLine = new ArrayList<String>();
		cityLine.add(city.getId()!=null?String.valueOf(city.getId()):"");
		cityLine.add(city.getName());
		cityLine.add(city.getType());
		cityLine.add((city.getGeoPosition()!=null && city.getGeoPosition().getLatitude()!=null)?String.valueOf(city.getGeoPosition().getLatitude()):"");
		cityLine.add((city.getGeoPosition()!=null && city.getGeoPosition().getLongitude()!=null)?String.valueOf(city.getGeoPosition().getLongitude()):"");
		
		return cityLine;
	}
}
