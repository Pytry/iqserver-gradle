package org.xitikit.iqserver.gradle.scan

import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskAction
import org.xitikit.iqserver.gradle.IqServerData
import org.xitikit.iqserver.gradle.IqServerDataValidator

import static org.xitikit.iqserver.gradle.IqServerDataResolver.resolve
import static org.xitikit.iqserver.gradle.scan.IqScanTaskHelper.configure

/**
 * Scans the project output and uploads the data to the configured Nexus IQ Server.
 */
class IqScanTask extends JavaExec {

    private IqServerData iqServerData

    @Override
    @TaskAction
    void exec() {
        resolve(
            this.project,
            iqServerData
        )
        configure(
            this,
            iqServerData
        )
        IqServerDataValidator.validate(this)
        super.exec()
    }

    IqServerData getIqServerData() {
        return iqServerData
    }

    void setIqServerData(final IqServerData iqServerData) {
        this.iqServerData = iqServerData
    }
}
