package org.xitikit.iqserver.gradle

import org.xitikit.iqserver.gradle.scan.IqScanTask

import javax.annotation.Nonnull

import static org.xitikit.iqserver.gradle.IqServerExtensionProperties.*

final class IqServerDataValidator {

    private final List<String> errors

    private final IqScanTask iqScanTask

    private final IqServerData iqServerData

    private IqServerDataValidator(IqScanTask iqScanTask) {
        errors = new ArrayList<>()
        this.iqScanTask = iqScanTask
        this.iqServerData = iqScanTask.iqServerData
    }

    static void validate(@Nonnull final IqScanTask iqScanTask) {
        new IqServerDataValidator(iqScanTask).
            url().
            appId().
            phase().
            target().
            check()
    }

    private IqServerDataValidator url() {
        if (iqServerData.url == null || iqServerData.url.trim() == '') {
            errors.add("'${IQ_SERVER_URL}' is required.")
        }
        this
    }

    private IqServerDataValidator appId() {
        if (iqServerData.appId == null || iqServerData.appId.trim() == '') {
            errors.add("'${IQ_SERVER_APP_ID}' is required.")
        }
        this
    }

    private IqServerDataValidator phase() {
        if (iqServerData.phase == null || iqServerData.phase.trim() == '') {
            errors.add("'${IQ_SERVER_PHASE}' is required.")
        }
        this
    }

    private IqServerDataValidator target() {
        if (iqServerData.target == null || iqServerData.target.trim() == '') {
            errors.add("'${IQ_SERVER_TARGET}' is required.")
        }
        this
    }

    private void check() {
        if (errors.size() > 0) {
            String message = 'There were configuration errors in the IqServerPlugin.'
            for (String error : errors) {
                message += " " + error
            }
            throw new IllegalArgumentException(message)
        }
    }
}
