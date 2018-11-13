# IQ Server Gradle Plugin
A gradle plugin for open source software governance with Nexus IQ Server.

This project is just barely getting started, so there's lots to be done.
Basic scan functionality is working, but the unit and integration tests could be fleshed out a lot more. 
The author is still new to groovy, spoc, and testing gradle plugins in general, so be kind :)

It works by downloading the configured version of the nexus-iq-server-cli tool, and wrapping it in a gradle "JavaExec" task.

## DSL

'xitikit.iqserver-gradle' provides an 'iqserver' dsl. Every property that is made available can be overwritten 
using 'gradle.properties'. Alternatively, the application can be configured to not allow the values to be changed
via the standard gradle property overrides by setting 'iqserver.ignoreGradleProperties' to true. The best approach 
is to only define the applications id in the dsl, and either pass the properties along the commandline, or set them in 
an ignored "gradle.properties" file.

#### Available Properties

`iqserver.url` -> The base url of the IQ Server instance.

`iqserver.appId` -> The application id as set in IQ Server.

`iqserver.target` -> Default: "libs". This is the path to the directory or archive that should be used as the basis for the scan. 
This is always treated as relative to the build directory.

`iqserver.phase` -> Default: "DEVELOP". This is the lifecycle phase to which the scans report should be attached. 
Possible values: "DEVELOP", "BUILD", "STAGE", "RELEASE", "OPERATE"

`iqserver.username` -> Optional. This is the developers username. Do not include this value if you have configured your 
JVM and IQ Server to use PKI.

`iqserver.password` -> Optional. This is the developers password. Do not include this value if you have configured your 
JVM and IQ Server to use PKI.

## Tasks

A task called "iqScan" will be created. This depends on the "assemble" target. The plugin 
does not set any dependency on this task so that the project owner can decide whether to
always run this as part of the build or check tasks.

## Examples

build.gradle

    plugins{
      id 'xitikit.iqserver-gradle' version '0.0.0.M1'
    }
  
    iqserver{
      appId='your-app-id' 
      target='libs'
    }

gradle.properties

    iqserver.url=http://must.supply.your.own:8070 //Required.
    iqserver.username=leaveOutForPkiAuthentication 
    iqserver.password=leaveOutForPkiAuthentication 
    iqserver.phase=DEVELOP // DEVELOP, STAGE, RELEASE, OPERATE

Making the "build" task depend on "iqScan".

    tasks['build'].dependsOn 'iqScan'
   

