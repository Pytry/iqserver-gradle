package org.xitikit.iqserver.gradle

import org.gradle.api.Project

import javax.annotation.Nonnull

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
        if (project.hasProperty('iqserver.url')) {
            String url = project.property(
                'iqserver.url'
            ) as String
            iqServerData.setUrl(url)
        }
    }

    private void resolveAppId() {
        if (project.hasProperty('iqserver.appId')) {
            iqServerData.setAppId(
                project.property(
                    'iqserver.appId'
                ) as String
            )
        }
    }

    private void resolveUsername() {

        if (project.hasProperty('iqserver.username')) {
            iqServerData.setUsername(
                project.property(
                    'iqserver.username'
                ) as String
            )
        }
    }

    private void resolvePassword() {
        if (project.hasProperty('iqserver.password')) {
            iqServerData.setPassword(
                project.property(
                    'iqserver.password'
                ) as String
            )
        }
    }

    private void resolvePhase() {
        if (project.hasProperty('iqserver.phase')) {
            iqServerData.setPhase(
                project.property(
                    'iqserver.phase'
                ) as String
            )
        }
    }

    private void resolveTarget() {
        if (project.hasProperty('iqserver.target')) {
            iqServerData.setTarget(
                project.property(
                    'iqserver.target'
                ) as String
            )
        }
    }

    private void resolveIgnoreGradleProperties() {
        if (project.hasProperty('iqserver.ignoreGradleProperties')) {
            iqServerData.setIgnoreGradleProperties(
                project.property(
                    'iqserver.ignoreGradleProperties'
                ) as boolean
            )
        }
    }
}
