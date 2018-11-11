package groovy.org.xitikit.iqserver.gradle

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class IqServerDataResolverTest extends Specification {

    @Rule
    TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile

    File gradleProperties

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        gradleProperties = testProjectDir.newFile('gradle.properties')
        buildFile << """
            apply plugin: 'xitikit.iqserver-gradle'
            iqserver {
                url='whatever'
                appId='whatever'
                username='whatever'
                password='whatever'
                phase='whatever'
                project='whatever'
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

    def "ResolveIqServerData should overwrite iqserver dsl"() {
        when:
        def result = ProjectBuilder.
            builder().
            withProjectDir(testProjectDir.root).
            build()
        then:
        result.plugins.hasPlugin('xitikit.iqserver-gradle')
        result.project.iqserver.url == 'https://localhost:8070'
        result.project.iqserver.appId == 'xitikit.iqserver-gradle'
        result.project.iqserver.username == 'test'
        result.project.iqserver.password == 'test'
        result.project.iqserver.phase == 'BUILD'
        result.project.iqserver.target == 'test'
        result.project.iqserver.testCompileScan
        result.project.iqserver.testRuntimeScan
        !result.project.iqserver.ignoreGradleProperties
        !result.project.iqserver.compileScan
        !result.project.iqserver.runtimeScan
    }
}
