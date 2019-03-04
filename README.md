# seerapi-client-java
[![CircleCI](https://circleci.com/gh/imsweb/seerapi-client-java.svg?style=shield)](https://circleci.com/gh/imsweb/seerapi-client-java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.imsweb/seerapi-client-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.imsweb/seerapi-client-java)

A [SEER*API](https://api.seer.cancer.gov) client for Java applications.  This library supports most of the APIs and
makes them easy to incorporate into Java applications.

## SEER

The Surveillance, Epidemiology and End Results ([SEER](http://seer.cancer.gov)) Program is a premier source for cancer statistics 
in the United States. The SEER Program collects information on incidence, prevalence and survival from specific geographic areas 
representing 28 percent of the US population and reports on all these data plus cancer mortality data for the entire country.

From the [SEER*API](https://api.seer.cancer.gov) website:

> The [SEER*API](https://api.seer.cancer.gov) is a RESTful Web service that supports various SEER Program data sets
> and algorithms. This service is available to developers who wish to incorporate [SEER](http://seer.cancer.gov) resources
> to their own  systems. These resources include databases and tools developed to enhance registry operations and quality
> improvement. The [SEER*API](https://api.seer.cancer.gov) is designed for integration into registry computer
> stems and is not designed to be accessed by end-users.

## Download

The library requires Java 8 or greater.

Download [the latest JAR][1] or grab via Maven:

```xml
<dependency>
    <groupId>com.imsweb</groupId>
    <artifactId>seerapi-client-java</artifactId>
    <version>3.14</version>
</dependency>
```

or via Gradle:

```
compile 'com.imsweb:seerapi-client-java:3.14'
```

## Usage

SEER*API usage is free, but requires an API key.  To get started,

1. Create a free account from the [Account Creation](https://api.seer.cancer.gov/showNewAccount.do) page
2. Locate your API key on your Account page

Your API key will need to be supplied to make calls.  It can be supplied on each call, or it can be stored in a
configuration file in your home directory called `.seerapi`.  The file should look like this

```
apikey=your_api_key
```

To make calls to the API, first get an instance of `SeerApi`.  If your local configuration is set up, then this is how you get a
connection.

```java
SeerApi api = new SeerApi.Builder().connect();
```

or the key can be supplied when constructing the instance.

```java
SeerApi api = new SeerApi.Builder().apiKey("your_api_key").connect();
```

Each set of APIs are broken into their own service.  Here are the services:

### Glossary (rest/glossary)

A glossary of cancer-related terms.

```java
api.glossary().getById("latest", "4ffd7623a3dd635c99d38e2d").execute().body()
```

### Disease (rest/disease)

A searchable database of hematopoietic and lymphoid neoplasms and solid tumor diseases.

```java
api.disease().samePrimaries("9870/3", "9872/3", "2010").execute().body()
```

### Multiple Primaries (rest/mph)

The SEER implementation of the Multiple Primary and Histology Coding Rules. The implementation combines Hematopoietic rules, SEER Multiple Primary and Histology Coding Rules. The rules used in the calculation are based on the diagnosis year and histology.

```java
MphInput input1 = new MphInput();
input1.setPrimarySite("C509");
input1.setHistologyIcdO3("8000");
input1.setBehaviorIcdO3("3");
input1.setDateOfDiagnosisYear("2016");
input1.setLaterality("1");

MphInput input2 = new MphInput();
input2.setPrimarySite("C501");
input2.setHistologyIcdO3("8000");
input2.setBehaviorIcdO3("3");
input2.setDateOfDiagnosisYear("2015");
input2.setLaterality("1");

MphResult result = api.mph(new MphInputPair(input1, input2)).execute().body();
```

### NAACCR (rest/naaccr)

The NAACCR API provides programmatic access to documentation for the NAACCR Standards for Cancer Registries Volume II. It includes
field level documentation as well as information about file layout.

```java
api.naaccr().field("14", 12).execute().body()
```

### NDC (rest/ndc)

A searchable mirror of the [National Drug Code Directory](http://www.fda.gov/Drugs/InformationOnDrugs/ucm142438.htm) from the FDA website. This database is updated weekly.

```java
api.ndc().getByCode("0002-3227").execute().body()
```

### RX (rest/rx)

A searchable database for coding oncology drug and regimen treatment categories in cancer registries.

```java
api.rx().getById("latest", "53c44b01102c1290262dc8b2").execute().body()
```

### SEER Incidence Site Recode (rest/recode)

The values of SEER site recode variables are based on the primary site and histology data fields submitted to SEER by the
registries. The site recode variables define the major cancer site/histology groups that are commonly used in the reporting of
cancer incidence data. For example, there is a section of the SEER Cancer Statistics Review for each major site corresponding to
groupings in a site recode variable. The site recode variables are added to SEER databases as a convenience for researchers.

```java
api.siteRecode().siteGroup("C619", "8000").execute().body()
```

### Site-specific Surgery Codes (rest/surgery)

The site-specific surgery tables available on the SEER website.

```java
api.surgery().tables().execute().body()
```

### Staging

Cancer staging algorithms.  Currently supports Collaborative Stage which is a unified data collection system designed to provide a
common data set to meet the needs of all three staging systems (TNM, SEER EOD, and SEER SS). It provides a comprehensive system to
improve data quality by standardizing rules for timing, clinical and pathologic assessments, and compatibility across all of the
systems for all cancer sites.

```
api.staging().schemaById("cs", "02.05.50", "brain").execute().body()
```

For a complete description of all available API inputs and outputs, see the SEER*API
[Documentation](https://api.seer.cancer.gov/docs) page.

[1]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.imsweb&a=seerapi-client-java&v=LATEST
