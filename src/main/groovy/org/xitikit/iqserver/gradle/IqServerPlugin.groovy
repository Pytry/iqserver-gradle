package org.xitikit.iqserver.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.xitikit.iqserver.gradle.prepare.IqScanPrepareTaskBuilder
import org.xitikit.iqserver.gradle.scan.IqScanTaskBuilder

import javax.annotation.Nonnull

import static org.xitikit.iqserver.gradle.IqServerDataResolver.*

class IqServerPlugin implements Plugin<Project> {

    @Override
    void apply(@Nonnull final Project target) {
        configure(target, createIqExt(target))
    }

    private static configure(
        @Nonnull final Project target,
        @Nonnull final IqServerData iqExt) {
        buildIqScanPrepare(target, iqExt)
        buildIqScan(target, iqExt)
    }

    static void buildIqScanPrepare(final Project target, final IqServerData iqExt) {
        new IqScanPrepareTaskBuilder(target, iqExt).buildIqScanPrepare()
    }

    private static IqServerData createIqExt(@Nonnull final Project project) {
        return resolveIqServerData(

            project,
            project.getExtensions().
                create(
                    "iqserver",
                    IqServerExtension.class
                ).iqServerData as IqServerData
        )
    }



    private static void buildIqScan(
        @Nonnull final Project target,
        @Nonnull final IqServerData iqExt) {
        new IqScanTaskBuilder(target, iqExt).buildIqScan()
    }
}

