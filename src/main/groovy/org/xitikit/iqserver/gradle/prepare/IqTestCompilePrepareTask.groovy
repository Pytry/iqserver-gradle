package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerData

class IqTestCompilePrepareTask extends DefaultTask
{
    IqServerData iqExt

    @TaskAction
    void copyTestCompileClasspath()
    {
        if (iqExt.testCompileScan) {
            new Copy().
                from(project.configurations.testCompileClasspath).
                into("${project.buildDir}/${iqExt.target}/lib/testCompileScan").
                execute()
        }
    }
}
