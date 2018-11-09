package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerPluginExtension

class IqTestRuntimeScanPrepareTask extends DefaultTask
{
    IqServerPluginExtension iqExt

    @TaskAction
    void copyTestRuntimeClasspath()
    {
        if (iqExt.testRuntimeScan) {
            new Copy().
                from(project.configurations.testRuntimeClasspath).
                into("${project.buildDir}/iqserver-scan/lib/testRuntimeScan").
                execute()
        }
    }
}
