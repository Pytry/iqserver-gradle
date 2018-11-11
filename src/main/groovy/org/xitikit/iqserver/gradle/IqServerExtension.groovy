package org.xitikit.iqserver.gradle

import org.gradle.api.Action

class IqServerExtension
{
    private File outputDir

    private final IqServerData  iqServerData = new IqServerData()

    void iqServerData(Action<? super IqServerData> action) {
        action.execute(iqServerData)
    }

    IqServerData getIqServerData() {
        return iqServerData
    }
}
