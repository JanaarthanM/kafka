# kafka
Contains kafka examples



About avro tools jar
Download the avro-tools-1.11.2.jar from maven central repository and use it as a java jar file.

Syntax:
java -jar avro-tools-1.11.2.jar <options> <avro_file.avro>

Example:
java -jar avro-tools-1.11.2.jar   
Version 1.11.2 of Apache Avro
Copyright 2010-2015 The Apache Software Foundation

This product includes software developed at
The Apache Software Foundation (https://www.apache.org/).
----------------
Available tools:
    canonical  Converts an Avro Schema to its canonical form
          cat  Extracts samples from files
      compile  Generates Java code for the given schema.
       concat  Concatenates avro files without re-compressing.
        count  Counts the records in avro files or folders
  fingerprint  Returns the fingerprint for the schemas.
   fragtojson  Renders a binary-encoded Avro datum as JSON.
     fromjson  Reads JSON records and writes an Avro data file.
     fromtext  Imports a text file into an avro data file.
      getmeta  Prints out the metadata of an Avro data file.
    getschema  Prints out schema of an Avro data file.
          idl  Generates a JSON schema from an Avro IDL file
 idl2schemata  Extract JSON schemata of the types from an Avro IDL file
       induce  Induce schema/protocol from Java class/interface via reflection.
   jsontofrag  Renders a JSON-encoded Avro datum as binary.
       random  Creates a file with randomly generated instances of a schema.
      recodec  Alters the codec of a data file.
       repair  Recovers data from a corrupt Avro Data file
  rpcprotocol  Output the protocol of a RPC service
   rpcreceive  Opens an RPC Server and listens for one message.
      rpcsend  Sends a single RPC message.
       tether  Run a tethered mapreduce job.
       tojson  Dumps an Avro data file as JSON, record per line or pretty.
       totext  Converts an Avro data file to a text file.
     totrevni  Converts an Avro data file to a Trevni file.
  trevni_meta  Dumps a Trevni file's metadata as JSON.
trevni_random  Create a Trevni file filled with random instances of a schema.
trevni_tojson  Dumps a Trevni file as JSON.


To read an avro file and print in json format:

java -jar avro-tools-1.11.2.jar tojson --pretty /Users/janaarthanm/Documents/java_workspaces_eclipse/hibernate/kafka/src/main/resources/specificRecord/output_data.avro
23/09/05 10:16:45 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
{
  "first_name" : "John",
  "last_name" : "Abraham",
  "age" : 39,
  "height" : 5.11,
  "weight" : 90.2,
  "automated_email" : false
}
{
  "first_name" : "Rajesh",
  "last_name" : "Reddy",
  "age" : 40,
  "height" : 5.6,
  "weight" : 80.1,
  "automated_email" : true
}


To get the schema of an avro file:

java -jar avro-tools-1.11.2.jar getschema /Users/janaarthanm/Documents/java_workspaces_eclipse/hibernate/kafka/src/main/resources/specificRecord/output_data.avro
23/09/05 10:18:55 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
{
  "type" : "record",
  "name" : "Customer",
  "namespace" : "com.example",
  "doc" : "Avro Schema for our Customer",
  "fields" : [ {
    "name" : "first_name",
    "type" : "string",
    "doc" : "First Name of Customer"
  }, {
    "name" : "last_name",
    "type" : "string",
    "doc" : "Last Name of Customer"
  }, {
    "name" : "age",
    "type" : "int",
    "doc" : "Age at the time of registration"
  }, {
    "name" : "height",
    "type" : "float",
    "doc" : "Height at the time of registration in cm"
  }, {
    "name" : "weight",
    "type" : "float",
    "doc" : "Weight at the time of registration in kg"
  }, {
    "name" : "automated_email",
    "type" : "boolean",
    "doc" : "Field indicating if the user is enrolled in marketing emails",
    "default" : true
  } ]
}

------------------------------------------------------------------------------------------------------------
Revisit Schema Evolution after learning schema registry - !!! Important !!!

