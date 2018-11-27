package org.xitikit.iqserver.gradle.scan

import org.gradle.api.GradleException
import org.xitikit.iqserver.gradle.IqServerData

import javax.annotation.Nonnull

/**
 * Helps configure the 'iqScan' with the values set in the dsl or properties.
 */
final class IqScanTaskHelper {

    private static final String IQ_CLI_CLASSPATH = "com.sonatype.insight.scan.cli.PolicyEvaluatorCli"

    private final IqScanTask task

    private final IqServerData iqServerData

    private final List<String> args = new ArrayList<>()

    private IqScanTaskHelper(
        @Nonnull final IqScanTask task,
        @Nonnull final IqServerData iqServerData) {
        this.task = task
        this.iqServerData = iqServerData
    }

    static void configure(@Nonnull final IqScanTask task,
                          @Nonnull final IqServerData iqServerData) {
        new IqScanTaskHelper(
            task,
            iqServerData
        ).configureIqScanTask()
    }

    private void configureIqScanTask() {
        checkIfClassExists(IQ_CLI_CLASSPATH)
        buildArgs()
        task.main = IQ_CLI_CLASSPATH
        task.classpath = task.project.buildscript.configurations.classpath
        task.args = this.args
    }

    private static void checkIfClassExists(final String className) {
        try {
            Class.forName(className)
        }
        catch (ClassNotFoundException ex) {
            throw new GradleException("IQ Server CLI not accessible", ex.cause)
        }
    }

    private void buildArgs() {
        buildUrl()
        buildAppId()
        buildPhase()
        buildAuth()
        buildTarget()
    }

    private void buildTarget() {
        if (iqServerData.target == null || iqServerData.target.trim() == "") {
            args.add("$task.sourceSets.main.output" as String)
        } else {

        }
        args.add("${iqServerData.target}" as String)
    }

    private void buildPhase() {
        args.add '-t'
        args.add "${iqServerData.phase}"
    }

    private void buildAuth() {
        if (useBasicAuth(iqServerData)) {
            args.add '-a'
            args.add "${iqServerData.username}:${iqServerData.password}" as String
        } else {
            args.add '--pki-authentication'
        }
    }

    private void buildAppId() {
        args.add '-i'
        args.add "${iqServerData.appId}" as String
    }

    private void buildUrl() {
        args.add '-s'
        args.add "${iqServerData.url}"
    }

    private static boolean useBasicAuth(@Nonnull IqServerData iqServerData) {
        iqServerData.password != null &&
            iqServerData.password.trim() != '' &&
            iqServerData.username != null &&
            iqServerData.username.trim() != ''
    }

    private static String stripLeadingSlash(@Nonnull String value) {
        if (value.startsWith("/") || value.startsWith("\\")) {
            return value.substring(1, value.length())
        }
        return value
    }
}
