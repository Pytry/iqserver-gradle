package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerPluginExtension

class IqRuntimePrepareTask extends DefaultTask
{
    IqServerPluginExtension iqExt

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
