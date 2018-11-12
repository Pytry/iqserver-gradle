package groovy.org.xitikit.iqserver.gradle

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.xitikit.iqserver.gradle.IqServerPlugin
import spock.lang.Specification

class PluginIsAppliedTest extends Specification {

    @Rule
    TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile

    File gradleProperties

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        gradleProperties = testProjectDir.newFile('gradle.properties')
        buildFile << """
plugins {
    id 'java-library'
    
}
apply plugin: 'org.xitikit.iqserver.gradle.IqServerPlugin'

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

    def "Plugin can be applied."() {
        given:
        def result = ProjectBuilder.builder().
            withProjectDir(testProjectDir.root).
            withName("PluginIsAppliedTest").
            build()
        when:
        result.pluginManager.apply 'xitikit.iqserver-gradle'
        then:
        result.project.plugins.hasPlugin(IqServerPlugin)
    }
}
