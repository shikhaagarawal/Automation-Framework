# Automation-Framework

This framework uses Spring, Apache POI, core java,selenium web driver and auto-it. Support chrome and IE as of now. But can be extended for any number of browser by adding selenium supported browser executable and adding small amount of code to read respective exe. This framework is designed only for windows.

1. Framework will read all test cases marked as 'Y' under RunManager.xlsx.
2. Code will search for all test cases under TestScripts\<test script name>.
3. Positie and negative test cases can be managed in single xls using multiple sheet option.
4. Application URL is configurable in RunManaget.xlsx
5. Mutiple user login/user role can be configured in RunManager.xlsx 


