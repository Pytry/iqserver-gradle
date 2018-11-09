package org.xitikit.iqserver.gradle

class IqServerPluginExtension
{
    String appId

    String url

    String username

    String password

    String phase = 'DEVELOP'

    String target = 'iqserver-scan'

    boolean compileScan = true

    boolean runtimeScan = true

    boolean testCompileScan = false

    boolean testRuntimeScan = false
}
