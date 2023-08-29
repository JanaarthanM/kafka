package com.janaa.kafkaExamples.avro;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumWriter;

import com.opencsv.CSVReader;

public class CsvToAvroFile {

	public static void main(String[] args) throws Exception {
		CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/student/input/student.csv"));
		List<String[]> csvFileRecords = csvReader.readAll();
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(new File("src/main/resources/student/input/student.avsc"));
		GenericRecordBuilder studentBuilder = new GenericRecordBuilder(schema);
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
		dataFileWriter.create(schema, new File("src/main/resources/student/output/student.avro"));
		for(String[] record : csvFileRecords) {
			studentBuilder.set("studentName", record[0]);
			studentBuilder.set("studentMark", Integer.parseInt(record[1]));
			studentBuilder.set("pass", Boolean.parseBoolean(record[2]));
			GenericData.Record data = studentBuilder.build();
			dataFileWriter.append(data);
		}
		System.out.println("Successfully written avro file");
		dataFileWriter.close();

	}

}
