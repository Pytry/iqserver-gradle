package org.xitikit.iqserver.gradle.scan

import org.gradle.api.Project
import org.xitikit.iqserver.gradle.IqServerPluginExtension

import javax.annotation.Nonnull

final class IqScanTaskBuilder
{
    @Nonnull
    private final Project target

    @Nonnull
    private final IqServerPluginExtension iqExt

    IqScanTaskBuilder(
        @Nonnull final Project target,
        @Nonnull final IqServerPluginExtension iqExt)
    {
        this.target = target
        this.iqExt = iqExt
    }

    void buildIqScan()
    {
        target.getTasks().
            create(
                "iqScan",
                IqScanTask.class,
                {
                    configureIqScanTask(it)
                })
    }

    private void configureIqScanTask(@Nonnull IqScanTask it)
    {
        it.dependsOn('assemble')
        it.main = 'com.sonatype.insight.scan.cli.PolicyEvaluatorCli'
        // arguments to pass to the IQ CLI
        addUrl(it)
        addAppId(it)
        addPhase(it)
        addAuth(it)
        addTarget(it)
    }

    private void addTarget(final IqScanTask it)
    {
        if (iqExt.target == null || iqExt.target == '') {
            it.args "${target.buildDir}/${stripLeadingSlash(iqExt.target)}"
        }
    }

    private static String stripLeadingSlash(@Nonnull String value)
    {
        if (value.startsWith("/") || value.startsWith("\\")) {
            return value.substring(1, value.length())
        }
        return value
    }

    private void addPhase(final IqScanTask it)
    {
        it.args '-t', iqExt.phase
    }

    private void addAuth(final IqScanTask it)
    {
        if (useBasicAuth(iqExt)) {
            it.args '-a', "${iqExt.username}:${iqExt.password}"
        } else {
            it.args '--pki-authentication'
        }
    }

    private void addAppId(@Nonnull IqScanTask it)
    {
        if (iqExt.appId = null || iqExt.appId.trim() == '') {
            throw new IllegalArgumentException("'url' is required in 'iqserver'.")
        }
        it.args '-i', "${iqExt.appId}"
    }

    private void addUrl(@Nonnull IqScanTask it)
    {
        if (iqExt.url == null || iqExt.url.trim() == '') {
            throw new IllegalArgumentException("'url' is required in 'iqserver'.")
        }
        it.args '-s', "${iqExt.url}"
    }

    private static boolean useBasicAuth(@Nonnull IqServerPluginExtension iqExt)
    {
        iqExt.password != null &&
            iqExt.password.trim() != '' &&
            iqExt.username != null &&
            iqExt.username.trim() != ''
    }
}
