package org.xitikit.iqserver.gradle

import org.gradle.api.Project

import javax.annotation.Nonnull

import static org.xitikit.iqserver.gradle.IqServerExtensionProperties.*

/**
 * Provides resolution between values set in the dsl and project properties.
 */
final class IqServerDataResolver {

    private final Project project

    private final IqServerData iqServerData

    private IqServerDataResolver(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {
        this.project = project
        this.iqServerData = iqServerData
    }

    /**
     * Resolves between values set in the dsl and project properties.
     * @param project the project as managed by gradle.
     * @param iqServerData the data injected by the 'iqserver' dsl.
     */
    static void resolve(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {
        new IqServerDataResolver(
            project,
            iqServerData
        ).resolveIqServerData()
    }

    private void resolveIqServerData() {
        if (!iqServerData.ignoreGradleProperties) {
            resolveUrl()
            resolveAppId()
            resolveUsername()
            resolvePassword()
            resolvePhase()
            resolveTarget()
            resolveIgnoreGradleProperties()
        }
    }

    private void resolveUrl() {
        if (project.hasProperty(IQ_SERVER_URL)) {
            String url = project.property(
                IQ_SERVER_URL
            ) as String
            iqServerData.setUrl(url)
        }
    }

    private void resolveAppId() {
        if (project.hasProperty(IQ_SERVER_APP_ID)) {
            iqServerData.setAppId(
                project.property(
                    IQ_SERVER_APP_ID
                ) as String
            )
        }
    }

    private void resolveUsername() {

        if (project.hasProperty(IQ_SERVER_USERNAME)) {
            iqServerData.setUsername(
                project.property(
                    IQ_SERVER_USERNAME
                ) as String
            )
        }
    }

    private void resolvePassword() {
        if (project.hasProperty(IQ_SERVER_PASSWORD)) {
            iqServerData.setPassword(
                project.property(
                    IQ_SERVER_PASSWORD
                ) as String
            )
        }
    }

    private void resolvePhase() {
        if (project.hasProperty(IQ_SERVER_PHASE)) {
            iqServerData.setPhase(
                project.property(
                    IQ_SERVER_PHASE
                ) as String
            )
        }
    }

    private void resolveTarget() {
        if (project.hasProperty(IQ_SERVER_TARGET)) {
            iqServerData.setTarget(
                project.property(
                    IQ_SERVER_TARGET
                ) as String
            )
        }
        else{
            iqServerData.setTarget("${project.rootProject.buildDir}/libs")
        }
    }

    private void resolveIgnoreGradleProperties() {
        if (project.hasProperty(IQ_SERVER_IGNORE_GRADLE_PROPERTIES)) {
            iqServerData.setIgnoreGradleProperties(
                project.property(
                    IQ_SERVER_IGNORE_GRADLE_PROPERTIES
                ) as boolean
            )
        }
    }
}
