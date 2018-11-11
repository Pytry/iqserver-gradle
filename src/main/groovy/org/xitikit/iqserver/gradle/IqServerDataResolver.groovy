package org.xitikit.iqserver.gradle

import org.gradle.api.Project

import javax.annotation.Nonnull

final class IqServerDataResolver {

    static IqServerData resolveIqServerData(
        @Nonnull final Project target,
        @Nonnull final IqServerData iqServerData) {

        if (!iqServerData.ignoreGradleProperties) {
            resolveUrl(target, iqServerData)
            resolveAppId(target, iqServerData)
            resolveUsername(target, iqServerData)
            resolvePassword(target, iqServerData)
            resolvePhase(target, iqServerData)
            resolveTarget(target, iqServerData)
            resolveCompileScan(target, iqServerData)
            resolveRuntimeScan(target, iqServerData)
            resolveRestCompileScan(target, iqServerData)
            resolveTestRuntimeScan(target, iqServerData)
            resolveIgnoreGradleProperties(target, iqServerData)
        }
        iqServerData
    }

    static IqServerData resolveUrl(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForString(project, iqServerData, 'iqserver.url')
        iqServerData
    }

    static def resolveAppId(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForString(project, iqServerData, 'iqserver.appId')
        iqServerData
    }

    static def resolveUsername(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForString(project, iqServerData, 'iqserver.Username')
        iqServerData
    }

    static def resolvePassword(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForString(project, iqServerData, 'iqserver.password')
        iqServerData
    }

    static def resolvePhase(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForString(project, iqServerData, 'iqserver.phase')
        iqServerData
    }

    static def resolveTarget(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForString(project, iqServerData, 'iqserver.project')
        iqServerData
    }

    static def resolveCompileScan(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForBoolean(project, iqServerData, 'iqserver.compileScan')
        iqServerData
    }

    static def resolveRuntimeScan(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForBoolean(project, iqServerData, 'iqserver.runtimeScan')
        iqServerData
    }

    static def resolveTestCompileScan(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForBoolean(project, iqServerData, 'iqserver.testCompileScan')
        iqServerData
    }

    static def resolveTestRuntimeScan(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForBoolean(project, iqServerData, 'iqserver.testRuntimeScan')
        iqServerData
    }

    static def resolveIgnoreGradleProperties(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData) {

        resolveForBoolean(project, iqServerData, 'iqserver.ignoreGradleProperties')
        iqServerData
    }

    private static void resolveForString(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData,
        @Nonnull final String key) {
        if (project.hasProperty(key)) {
            iqServerData[key] = project.project(key) as String
        }
    }

    private static void resolveForBoolean(
        @Nonnull final Project project,
        @Nonnull final IqServerData iqServerData,
        @Nonnull final String key) {
        if (project.hasProperty(key)) {
            iqServerData[key] = Boolean.valueOf(project.project(key) as boolean)
        }
    }
}
