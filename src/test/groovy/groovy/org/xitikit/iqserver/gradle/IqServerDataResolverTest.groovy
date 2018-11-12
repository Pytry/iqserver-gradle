package groovy.org.xitikit.iqserver.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.xitikit.iqserver.gradle.IqServerData
import org.xitikit.iqserver.gradle.IqServerDataResolver
import spock.lang.Specification

class IqServerDataResolverTest extends Specification {

    def "ResolveIqServerData should overwrite the dsl by default"() {
        when:
        Project result = ProjectBuilder.builder().build()
        result.extensions.extraProperties.addAll([
            'iqserver.url'     : 'https://localhost:8070',
            'iqserver.appId'   : 'xitikit.iqserver-gradle',
            'iqserver.username': 'test',
            'iqserver.password': 'test',
            'iqserver.phase'   : 'BUILD'
        ])
        IqServerData iqServerData = new IqServerData()
        IqServerDataResolver.resolve(result, iqServerData)

        then:
        iqServerData.url == 'https://localhost:8070'
        iqServerData.appId == 'xitikit.iqserver-gradle'
        iqServerData.username == 'test'
        iqServerData.password == 'test'
        iqServerData.phase == 'BUILD'
        iqServerData.target == 'test'
        !result.project.iqserver.ignoreGradleProperties
    }

    def "ResolveIqServerData should NOT overwrite the dsl when ignoreGradleProperties is true"() {
        when:
        Project result = ProjectBuilder.builder().build()
        result.extensions.extraProperties.addAll([
            'iqserver.url'     : 'https://localhost:8070',
            'iqserver.appId'   : 'xitikit.iqserver-gradle',
            'iqserver.username': 'test',
            'iqserver.password': 'test',
            'iqserver.phase'   : 'BUILD'
        ])
        IqServerData iqServerData = new IqServerData()
        iqServerData.ignoreGradleProperties = true
        IqServerDataResolver.resolve(result, iqServerData)

        then:
        iqServerData.url == null
        iqServerData.appId == null
        iqServerData.username == null
        iqServerData.password == null
        iqServerData.phase == 'DEVELOP'
        iqServerData.target == 'libs'
        result.project.iqserver.ignoreGradleProperties
    }
}
