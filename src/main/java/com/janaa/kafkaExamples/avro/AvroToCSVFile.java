package com.janaa.kafkaExamples.avro;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;

import com.opencsv.CSVWriter;

public class AvroToCSVFile {

	public static void main(String[] args) throws Exception {
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(
				new File("src/main/resources/student/avroToCsv/student.avro"), datumReader);
		CSVWriter csvWriter = new CSVWriter(new FileWriter("src/main/resources/student/avroToCsv/student.csv"));
		Iterator<GenericRecord> genericRecordIterator =  dataFileReader.iterator();
		while(genericRecordIterator.hasNext()) {
			GenericRecord genericRecord = genericRecordIterator.next();
			csvWriter.writeNext(new String[] {String.valueOf(genericRecord.get("studentName")), 
					String.valueOf(genericRecord.get("studentMark")), String.valueOf(genericRecord.get("pass"))});
		}
		csvWriter.flush();
		csvWriter.close();

	}

}
