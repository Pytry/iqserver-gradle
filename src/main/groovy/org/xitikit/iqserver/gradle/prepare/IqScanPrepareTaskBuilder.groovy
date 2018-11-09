package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.Project
import org.xitikit.iqserver.gradle.IqServerPluginExtension

import javax.annotation.Nonnull

final class IqScanPrepareTaskBuilder
{
    private final Project target

    private final IqServerPluginExtension iqExt

    IqScanPrepareTaskBuilder(
        @Nonnull Project target,
        @Nonnull IqServerPluginExtension iqExt)
    {
        this.target = target
        this.iqExt = iqExt
    }

    void buildIqScanPrepare()
    {
        target.getTasks().
            create(
                "iqScanPrepare",
                IqScanPrepareTask.class,
                {})
    }
}
