package org.xitikit.iqserver.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.xitikit.iqserver.gradle.prepare.IqScanPrepareTaskBuilder
import org.xitikit.iqserver.gradle.scan.IqScanTaskBuilder

import javax.annotation.Nonnull

class IqServerPlugin implements Plugin<Project> {

    @Override
    void apply(@Nonnull final Project target) {
        configure(target, createIqExt(target))
    }

    private static configure(
        @Nonnull final Project target,
        @Nonnull final IqServerPluginExtension iqExt) {
        buildIqScanPrepare(target, iqExt)
        buildIqScan(target, iqExt)
    }

    static void buildIqScanPrepare(final Project target, final IqServerPluginExtension iqExt) {
        new IqScanPrepareTaskBuilder(target, iqExt).buildIqScanPrepare()
    }

    private static IqServerPluginExtension createIqExt(@Nonnull final Project target) {
        return target.getExtensions().
            create(
                "iqserver",
                IqServerPluginExtension.class)
    }

    private static void buildIqScan(
        @Nonnull final Project target,
        @Nonnull final IqServerPluginExtension iqExt) {
        new IqScanTaskBuilder(target, iqExt).buildIqScan()
    }
}
