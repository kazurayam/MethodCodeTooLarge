Method Code too large!
=========================

This is a small Katalon Studio project.
This project reproduces an incident reported at a post in the Katalon Forum [Method Code too large!](https://forum.katalon.com/t/method-code-too-large/9475)

One katalon user spullabhotla posted his resource at
https://forum.katalon.com/t/method-code-too-large/9475/28
. With this project you can reproduce his incident.

Just execute Test Case `TS1`. It would take long seconds. When it stops, see the Console message. You will find "Method Code too large!" message.

Please note you would, in the `Libs` folder, you would find a very big `TempTestSuiteXXXXXXXXXXXXXXXX.groovy` file, which is the cause of the "Method Code too large!" error.
