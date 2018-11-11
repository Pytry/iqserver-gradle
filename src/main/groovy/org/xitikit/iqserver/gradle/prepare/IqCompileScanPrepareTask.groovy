package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerData

class IqCompileScanPrepareTask extends DefaultTask
{
    IqServerData iqExt

    @TaskAction
    void copyCompileClasspath()
    {
        if (iqExt.compileScan) {
            new Copy().
                from(project.configurations.compileClasspath).
                into("${project.buildDir}/${iqExt.target}/lib/compile").
                execute()
        }
    }
}
