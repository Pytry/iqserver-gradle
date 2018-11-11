package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerData

class IqArtifactsScanPrepareTask extends DefaultTask
{
    IqServerData iqExt

    @TaskAction
    void copyArtifacts()
    {
        new Copy().
            from(project.artifacts).
            into("${project.buildDir}/${iqExt.target}/artifacts").
            execute()
    }
}
