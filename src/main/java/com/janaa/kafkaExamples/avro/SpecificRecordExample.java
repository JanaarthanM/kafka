package com.janaa.kafkaExamples.avro;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import com.example.Customer;
import com.example.Customer.Builder;
import com.opencsv.CSVWriter;

/*
 * This example is to create .avro and .csv output files from an avro schema and input data
 * 
 * To execute SpecificRecordExample successfully follow these steps strictly
 * 
 * Add the dependency in maven org.codehaus.mojo as shown in the pom.xml file
 * Use the executions shown under org.apache.avro dependency in the same pom.xml file
 * Update the maven project
 * mvn clean package
 * Then use the com.example.Customer class in your class
 */
public class SpecificRecordExample {

	public static void main(String[] args) throws Exception {
		String inputData = new String(Files.readAllBytes(Paths.get("src/main/resources/specificRecord/input_data.txt")));
		CSVWriter csvWriter = new CSVWriter(new FileWriter("src/main/resources/specificRecord/output_data.csv"));
		csvWriter.writeNext(new String[] {"First_Name", "Last_name", "Age", "Height", "Weight", "Automated_Email"});
		StringTokenizer st = new StringTokenizer(inputData, "\n");
		st.nextToken();
		final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);
		DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter);
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(new File("src/main/resources/specificRecord/customer.avsc"));
		dataFileWriter.create(schema, new File("src/main/resources/specificRecord/output_data.avro"));
		while(st.hasMoreTokens()) {
			String line = st.nextToken();
			String[] colValues = line.split(",");
			String firstName = colValues[0];
			String lastName = colValues[1];
			String age = colValues[2];
			String height = colValues[3];
			String weight = colValues[4];
			String automatedEmail = colValues[5];
			Builder builder = Customer.newBuilder();
			builder.setFirstName(firstName);
			builder.setLastName(lastName);
			builder.setAge(Integer.parseInt(age));
			builder.setHeight(Float.parseFloat(height));
			builder.setWeight(Float.parseFloat(weight));
			builder.setAutomatedEmail(Boolean.parseBoolean(automatedEmail));
			Customer customer = builder.build();
			dataFileWriter.append(customer);
			csvWriter.writeNext(new String[] {customer.getFirstName(), customer.getLastName(), customer.getAge()+ "", customer.getHeight()+ "", customer.getWeight()+ "", customer.getAutomatedEmail()+ ""});
			csvWriter.flush();
		}
		csvWriter.close();
		dataFileWriter.close();
		System.out.println("Successfully created .csv and .avro files.");
	}

}
