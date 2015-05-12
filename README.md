Demo Crosswalk snapshot bug
-------

Demo test case for [a Crosswalk bug](https://crosswalk-project.org/jira/browse/XWALK-4206) causing us to be unable to take snapshots.

This app has three flavors:

* `withoutCrosswalk`
* `withCrosswalkArmv7`
* `withCrosswalkX86`

To install the no-Crosswalk (i.e. WebView) version, do:

    ./gradlew installWithoutCrosswalkDebug

To install the Crosswalk version on an Armv7 device, do:

    ./gradlew installWithCrosswalkArmv7Debug
    
To install the Crosswalk version on an X86 device, do:

    ./gradlew installWithCrosswalkX86Debug

You may need to uninstall the app to avoid version downgrade errors.