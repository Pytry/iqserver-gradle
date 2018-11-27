package org.xitikit.iqserver.gradle

import org.gradle.api.Plugin
import org.gradle.api.internal.project.ProjectInternal
import org.xitikit.iqserver.gradle.scan.IqScanTask
import org.xitikit.iqserver.gradle.scan.PrepareIqScanTask

import javax.annotation.Nonnull

import static org.xitikit.iqserver.gradle.IqServerExtensionProperties.*

class IqServerPlugin implements Plugin<ProjectInternal> {

    @Override
    void apply(@Nonnull final ProjectInternal project) {
        buildPrepareIqScan(project)
        buildIqScan(
            project,
            createIqServerData(project))
    }

    private static void buildPrepareIqScan(final ProjectInternal project) {
        project.tasks.create(
            PREPARE_IQ_SCAN,
            PrepareIqScanTask,
            {
                it.group = VERIFICATION
                it.dependsOn 'assemble'
            })
    }

    private static IqServerData createIqServerData(@Nonnull final ProjectInternal project) {
        project.extensions.create(
            IQ_SERVER,
            IqServerExtension,
            new IqServerData()
        ).iqServerData
    }

    private static void buildIqScan(
        @Nonnull final ProjectInternal project,
        @Nonnull final IqServerData iqServerData) {
        project.tasks.create(
            IQ_SCAN,
            IqScanTask,
            {
                it.iqServerData = iqServerData
                it.group = VERIFICATION
                it.dependsOn(PREPARE_IQ_SCAN)
            })
    }
}

