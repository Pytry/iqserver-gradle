package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerData

class IqScanPrepareTask extends Copy
{
    IqServerData iqExt

    @TaskAction
    void prepare()
    {
        copyTestRuntimeClasspath()
        copyRuntimeClasspath()
        copyTestCompileClasspath()
        copyCompileClasspath()
        copyArtifacts()
    }

    private void copyArtifacts()
    {
        new Copy().
            from(project.artifacts).
            into("${project.buildDir}/${iqExt.target}/artifacts").
            execute()
    }

    private void copyCompileClasspath()
    {
        if (iqExt.compileScan) {
            new Copy().
                from(project.configurations.compileClasspath).
                into("${project.buildDir}/${iqExt.target}/lib/compile").
                execute()
        }
    }

    private void copyTestCompileClasspath()
    {
        if (iqExt.testCompileScan) {
            new Copy().
                from(project.configurations.testCompileClasspath).
                into("${project.buildDir}/${iqExt.target}/lib/testCompileScan").
                execute()
        }
    }

    private void copyRuntimeClasspath()
    {
        if (iqExt.runtimeScan) {
            new Copy().
                from(project.configurations.runtimeClasspath).
                into("${project.buildDir}/${iqExt.target}/lib/runtime").
                execute()
        }
    }

    private void copyTestRuntimeClasspath()
    {
        if (iqExt.testRuntimeScan) {
            new Copy().
                from(project.configurations.testRuntimeClasspath).
                into("${project.buildDir}/iqserver-scan/lib/testRuntimeScan").
                execute()
        }
    }
}
