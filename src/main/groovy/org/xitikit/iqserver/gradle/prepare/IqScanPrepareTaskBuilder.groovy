package org.xitikit.iqserver.gradle.prepare

import org.gradle.api.Project
import org.xitikit.iqserver.gradle.IqServerData

import javax.annotation.Nonnull

final class IqScanPrepareTaskBuilder
{
    private final Project target

    private final IqServerData iqExt

    IqScanPrepareTaskBuilder(
        @Nonnull Project target,
        @Nonnull IqServerData iqExt)
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
