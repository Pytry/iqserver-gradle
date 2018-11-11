package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerData

class IqRuntimePrepareTask extends DefaultTask
{
    IqServerData iqExt

    @TaskAction
    void copyRuntimeClasspath()
    {
        if (iqExt.runtimeScan) {
            new Copy().
                from(project.configurations.runtimeClasspath).
                into("${project.buildDir}/${iqExt.target}/lib/runtime").
                execute()
        }
    }
}
