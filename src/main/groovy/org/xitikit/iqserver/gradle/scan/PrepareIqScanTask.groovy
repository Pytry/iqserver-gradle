package org.xitikit.iqserver.gradle.scan

import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerData

import java.util.stream.Collectors

/**
 * Scans the project output and uploads the data to the configured Nexus IQ Server.
 */
class PrepareIqScanTask extends Copy {

    PrepareIqScanTask() {
        super.dependsOn('assemble')
    }

    @TaskAction
    void exec() {
        super.from(project.subprojects.stream().
            map({
                "${it.sourceSets.main.output}" as String
            })
            .collect(Collectors.toList())
            .toArray())
        super.into("$project.rootProject.buildDir/libs")
        super.execute()
    }
}
