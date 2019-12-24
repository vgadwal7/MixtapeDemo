## MixtapeDemo
This programming exercise is to take in three inputs a **\<mixtape filename\>.json** file, a **\<changes filename\>.json** file and an **\<output filename\>.json** file and implement the changes
described in the **\<changes filename\>.json** and write it to the **\<output filename\>.json** file.

## Pre-requisites
### Environment
Jdk version 8 or above
### Format of changes file
Please use the changes.json sample file in the MixtapeDemoTest which has necessary comments as to how add inputs. <br/>

## How to run
The MixtapeDemoTest has a prebuilt jar MixtapeDemo.jar with a sample \<mixtape filename\>.json file, a \<changes filename\>.json file.Please ensure you have the **\<mixtape filename\>.json, \<changes filename\>.json, \<output filename\>.json** in the folder which has the jar file. The file can be run from the command line or terminal using the following command
<br>**java -jar MixtapeDemo.jar \<mixtape filename\>.json \<changes filename\>.json \<output filename\>.json**<br/>

To run the project on a local ide , download the project and on any ide of your choice import it as a maven based project and build it using the maven plugin use the command **mvn clean package** and an executable jar is created in the **\<project folder\>/../target** folder. Please ensure you have the **\<mixtape filename\>.json, \<changes filename\>.json, \<output filename\>.json** in the **\<project folder\>/../target**  folder. To run the jar on the command line or terminal use the below command
<br>**java -jar \<jar name\>.jar \<mixtape filename\>.json \<changes filename\>.json \<output filename\>.json**<br/>

To run the project if in case no ide is available have maven installed on your machine and navigate to the folder where the pom.xml is present over command line for Windows or terminal if using a Mac and use the command **mvn clean package** and an executable jar is created in the **\<project folder\>/../target** folder.Please ensure you have the **\<mixtape filename\>.json, \<changes filename\>.json, \<output filename\>.json** in the **\<project folder\>/../target**  folder.To run the jar on the command line or terminal use  the command
<br>**java -jar \<jar name\>.jar \<mixtape filename\>.json \<changes filename\>.json \<output filename\>.json**<br/>

## Performance and Scaling
The jar can be run as a standalone jar on any cloud environment and scaled up by adding more compute and ephemeral memory for the instance running the jvm. Example: running as a standalone jar on  Elastic Bean Stalk and scaling the environment based on the size of the inputs which translates to compute and storage of the environment being used to run the jvm running the jar on the underlying EC2 instance.  

## Future Enhancements
A level of performance gain in terms of number of objects being stored on the java heap and processing time can be acheived by further using java parallel streams for smaller operations when processing data from collections and also lazy loading of the objects itself.

Exposing the functionality as a stateleful rest webservice to process multiple change requests.

Persisting the mixtape metadata namely user,songs and playlists in a nosql database for multiple change requests in an existing session.Reads are faster for the nosqldb and can be used to process the changes faster giving a performance gain.

Currently supports only .json based input format extending the input for other file types.
