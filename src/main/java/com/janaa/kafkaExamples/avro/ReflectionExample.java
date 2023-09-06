package com.janaa.kafkaExamples.avro;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.Nullable;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumWriter;

public class ReflectionExample {

	public static void main(String[] args) throws Exception {
		Schema schema = ReflectData.get().getSchema(ReflectedCustomer.class);
		File file = new File("src/main/resources/reflection/customer-reflected.avro");
		DatumWriter<ReflectedCustomer> writer = new ReflectDatumWriter<>(ReflectedCustomer.class);
		DataFileWriter<ReflectedCustomer> out = new DataFileWriter<>(writer).setCodec(CodecFactory.deflateCodec(9))
				.create(schema, file);
		StringTokenizer st = new StringTokenizer(
				new String(Files.readAllBytes(Paths.get("src/main/resources/reflection/reflection_input.txt"))));
		st.nextToken();
		while (st.hasMoreTokens()) {
			String[] row = st.nextToken().split(",");
			out.append(new ReflectedCustomer(row[0], row[1], row[2]));
		}
		out.close();
		System.out.println("Successfully created avro file using reflection.");
	}
}

class ReflectedCustomer {

	private String first_name;
	private String last_name;
	@Nullable
	private String nick_name;

	public ReflectedCustomer() {
		super();
	}

	public ReflectedCustomer(String first_name, String last_name, String nick_name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.nick_name = nick_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

}
