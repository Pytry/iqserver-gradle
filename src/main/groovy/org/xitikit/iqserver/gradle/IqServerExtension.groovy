package org.xitikit.iqserver.gradle

import org.gradle.api.Action

class IqServerExtension {

    private File outputDir

    private final IqServerData iqServerData = new IqServerData()

    void iqServerData(Action<? super IqServerData> action) {
        action.execute(iqServerData)
    }

    IqServerData getIqServerData() {
        return iqServerData
    }

    File getOutputDir() {
        return outputDir
    }

    void setOutputDir(final File outputDir) {
        this.outputDir = outputDir
    }

    String getUrl() {
        return iqServerData.url
    }

    void setUrl(final String url) {
        iqServerData.url = url
    }

    String getAppId() {
        return iqServerData.appId
    }

    void setAppId(final String appId) {
        iqServerData.setAppId appId
    }

    String getUsername() {
        return iqServerData.username
    }

    void setUsername(final String username) {
        iqServerData.username = username
    }

    String getPassword() {
        return iqServerData.password
    }

    void setPassword(final String password) {
        iqServerData.password = password
    }

    String getPhase() {
        return iqServerData.phase
    }

    void setPhase(final String phase) {
        iqServerData.phase = phase
    }

    String getTarget() {
        return iqServerData.target
    }

    void setTarget(final String target) {
        iqServerData.target = target
    }

    boolean getCompileScan() {
        return iqServerData.compileScan
    }

    void setCompileScan(final boolean compileScan) {
        iqServerData.compileScan = compileScan
    }

    boolean getRuntimeScan() {
        return iqServerData.runtimeScan
    }

    void setRuntimeScan(final boolean runtimeScan) {
        iqServerData.runtimeScan = runtimeScan
    }

    boolean getTestCompileScan() {
        return iqServerData.testCompileScan
    }

    void setTestCompileScan(final boolean testCompileScan) {
        iqServerData.testCompileScan = testCompileScan
    }

    boolean getTestRuntimeScan() {
        return iqServerData.testRuntimeScan
    }

    void setTestRuntimeScan(final boolean testRuntimeScan) {
        iqServerData.testRuntimeScan = testRuntimeScan
    }

    boolean getIgnoreGradleProperties() {
        return iqServerData.ignoreGradleProperties
    }

    void setIgnoreGradleProperties(final boolean ignoreGradleProperties) {
        iqServerData.ignoreGradleProperties = ignoreGradleProperties
    }
}
