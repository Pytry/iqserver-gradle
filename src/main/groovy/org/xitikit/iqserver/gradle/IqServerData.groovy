package org.xitikit.iqserver.gradle

/**
 * Holds the configuration data for running scans against Sonatype Nexus IQ Server.
 * Values set in the "iqserver" dsl can be overwritten with their corresponding gradle property.
 */
class IqServerData {

    /**
     * The server url of the IQ Server.
     * Can be overwritten by "iqserver.url" in gradle.properties,
     * or by passing '-Piqserver.url=<value>' to the gradle runner.
     */

    private String url

    /**
     * The application ID as set in IQ Server.
     * Can be overwritten by "iqserver.appId" in gradle.properties,
     * or by passing '-Piqserver.appId=<value>' to the gradle runner.
     */

    private String appId

    /**
     * The username to use when authenticating against the IQ Server.
     *
     * If either the username or password are not provided, then
     * pki authentication is used instead (ir, the '--pki-authentication'
     * flag is passed to the scanner.
     *
     * Can be overwritten by "iqserver.username" in gradle.properties,
     * or by passing '-Piqserver.username=<value>' to the gradle runner.
     */

    private String username

    /**
     * The password to use when authenticating against the IQ Server.
     *
     * If either the username or password are not provided, then
     * pki authentication is used instead (ir, the '--pki-authentication'
     * flag is passed to the scanner.
     *
     * Can be overwritten by "iqserver.password" in gradle.properties,
     * or by passing '-Piqserver.password=<value>' to the gradle runner.
     */

    private String password

    /**
     * The IQ Server phase that the scan should be attached.
     * Defaults to 'DEVELOP'.
     */

    private String phase = 'DEVELOP'

    /**
     * The path to the directory or archive that should be scanned.
     * Values are treated as relative to the gradle build directory.
     * Default value is 'libs'.
     */

    private String target = 'libs'

    /**
     * Tells the plugin whether it should ignore values that were not set
     * in the 'iqserver' dsl. If set to true, then the property values
     * must be set in the gradle script. Values from the commandline,
     * project properties, and all properties files will be ignored.
     */

    private boolean ignoreGradleProperties = false

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

    boolean getIgnoreGradleProperties() {
        return ignoreGradleProperties
    }

    void setIgnoreGradleProperties(final boolean ignoreGradleProperties) {
        this.ignoreGradleProperties = ignoreGradleProperties
    }
}
