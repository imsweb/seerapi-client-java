# seerapi-client-java
[![Build Status](https://travis-ci.org/imsweb/seerapi-client-java.svg?branch=master)](https://travis-ci.org/imsweb/seerapi-client-java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.imsweb/seerapi-client-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.imsweb/seerapi-client-java)

A [SEER*API](https://api.seer.cancer.gov) client for Java applications.  This library supports most of the APIs and 
makes them easy to incorporate into Java applications.

## Supported APIs

### Collaborative Staging (rest/cstage)

Collaborative Stage is a unified data collection system designed to provide a common data set to meet the needs of all three staging systems (TNM, SEER EOD, and SEER SS). It provides a comprehensive system to improve data quality by standardizing rules for timing, clinical and pathologic assessments, and compatibility across all of the systems for all cancer sites.

### Disease (rest/disease)

A searchable database of hematopoietic and lymphoid neoplasms and solid tumor diseases.

### RX (rest/rx)

A searchable database for coding oncology drug and regimen treatment categories in cancer registries.

### Glossary (rest/glossary)

A glossary of cancer-related terms.

### NAACCR (rest/naaccr)

The NAACCR API provides programmatic access to documentation for the NAACCR Standards for Cancer Registries Volume II. It includes field level documentation as well as information about file layout.

### SEER Incidence Site Recode (rest/recode)

The values of SEER site recode variables are based on the primary site and histology data fields submitted to SEER by the registries. The site recode variables define the major cancer site/histology groups that are commonly used in the reporting of cancer incidence data. For example, there is a section of the SEER Cancer Statistics Review for each major site corresponding to groupings in a site recode variable. The site recode variables are added to SEER databases as a convenience for researchers.

### Site-specific Surgery Codes (rest/surgery)

The site-specific surgery tables available on the SEER website.

## SEER

The Surveillance, Epidemiology and End Results ([SEER](http://seer.cancer.gov)) Program is a premier source for cancer statistics in the United States. The SEER Program collects information on incidence, prevalence and survival from specific geographic areas representing 28 percent of the US population and reports on all these data plus cancer mortality data for the entire country.

From the [SEER*API](https://api.seer.cancer.gov) website:

> The [SEER*API](https://api.seer.cancer.gov) is a RESTful Web service that supports various SEER Program data sets
> and algorithms. This service is available to developers who wish to incorporate [SEER](http://seer.cancer.gov) resources
> to their own  systems. These resources include databases and tools developed to enhance registry operations and quality 
> improvement. The [SEER*API](https://api.seer.cancer.gov) is designed for integration into registry computer
> stems and is not designed to be accessed by end-users.

## Download

The library requires Java 6 or greater.

Download [the latest JAR][1] or grab via Maven:

```xml
<dependency>
    <groupId>com.imsweb</groupId>
    <artifactId>seerapi-client-java</artifactId>
    <version>1.7</version>
</dependency>
```

or via Gradle:

```
compile 'com.imsweb.com:seerapi-client-java:1.7'
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

Making calls to the API is as simple as this if you use the local configuration

```java
SeerApiBuilder builder = new SeerApiBuilder();
StagingSchema schema = builder.connect().stagingSchemaById("cs", "02.05.50", "brain");
```

or the key can be passed in the `connect` call.

```java
SeerApiBuilder builder = new SeerApiBuilder();
StagingSchema schema = builder.apiKey("your_api_key").connect().stagingSchemaById("cs", "02.05.50", "brain");
```

For a complete description of all available API inputs and outputs, see the SEER*API
[Documentation](https://api.seer.cancer.gov/api.do) page.

[1]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.imsweb&a=seerapi-client-java&v=LATEST
