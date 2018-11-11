package groovy.org.xitikit.iqserver.gradle

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class IqServerDataResolverWithoutOverridesTest extends Specification {

    @Rule
    TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile

    File gradleProperties

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        gradleProperties = testProjectDir.newFile('gradle.properties')
        buildFile << """
buildscript {
    dependencies {
        classpath group: 'org.xitikit.iqserver', name: "iqserver-gradle", version: '0.0.0-SNAPSHOT'
    }
}

plugins {
    id 'java-library'
}
apply plugin: 'xitikit.iqserver-gradle'

iqserver {    
    url 'whatever'
    appId 'whatever'
    phase 'whatever'
    project 'whatever'
    username 'whatever'
    password 'whatever'
    ignoreGradleProperties true
}
        """

        gradleProperties << """        
iqserver.url=https://localhost:8070
iqserver.appId=xitikit.iqserver-gradle
iqserver.username=test
iqserver.password=test
iqserver.phase=BUILD
iqserver.project=test
iqserver.compileScan=false
iqserver.runtimeScan=false
iqserver.testCompileScan=true
iqserver.testRuntimeScan=true
        """
    }

    def "ignoreGradleProperties should stop gradle.properties from overwriting iqserver dsl"() {
        when:
        def result = ProjectBuilder.
            builder().
            withProjectDir(testProjectDir.root).
            build()
        /**
         * Confirm gradle.properties override defaults.
         * **/
        then:
        result.
            getPluginManager().
            hasPlugin('xitikit.iqserver-gradle')
        result.project.iqserver.ignoreGradleProperties
        result.project.
            iqserver.url == 'whatever'
        result.project.
            iqserver.appId == 'whatever'
        result.project.
            iqserver.username == 'whatever'
        result.project.
            iqserver.password == 'whatever'
        result.project.
            iqserver.phase == 'whatever'
        result.project.
            iqserver.target == 'whatever'
        result.project.iqserver.compileScan
        result.project.iqserver.runtimeScan
        !result.project.iqserver.testCompileScan
        !result.project.iqserver.testRuntimeScan
    }
}
