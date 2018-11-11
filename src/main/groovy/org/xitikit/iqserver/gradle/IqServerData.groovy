package org.xitikit.iqserver.gradle

class IqServerData {

    String url

    String appId

    String username

    String password

    String phase = 'DEVELOP'

    String target = 'build/libs'

    boolean compileScan = false

    boolean runtimeScan = false

    boolean testCompileScan = false

    boolean testRuntimeScan = false

    boolean ignoreGradleProperties = false

    String getUrl() {
        return url
    }

    void setUrl(final String url) {
        this.url = url
    }

    String getAppId() {
        return appId
    }

    void setAppId(final String appId) {
        this.appId = appId
    }

    String getUsername() {
        return username
    }

    void setUsername(final String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(final String password) {
        this.password = password
    }

    String getPhase() {
        return phase
    }

    void setPhase(final String phase) {
        this.phase = phase
    }

    String getTarget() {
        return target
    }

    void setTarget(final String target) {
        this.target = target
    }

    boolean getCompileScan() {
        return compileScan
    }

    void setCompileScan(final boolean compileScan) {
        this.compileScan = compileScan
    }

    boolean getRuntimeScan() {
        return runtimeScan
    }

    void setRuntimeScan(final boolean runtimeScan) {
        this.runtimeScan = runtimeScan
    }

    boolean getTestCompileScan() {
        return testCompileScan
    }

    void setTestCompileScan(final boolean testCompileScan) {
        this.testCompileScan = testCompileScan
    }

    boolean getTestRuntimeScan() {
        return testRuntimeScan
    }

    void setTestRuntimeScan(final boolean testRuntimeScan) {
        this.testRuntimeScan = testRuntimeScan
    }

    boolean getIgnoreGradleProperties() {
        return ignoreGradleProperties
    }

    void setIgnoreGradleProperties(final boolean ignoreGradleProperties) {
        this.ignoreGradleProperties = ignoreGradleProperties
    }
}
