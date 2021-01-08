import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.text.TemplateEngine
import internal.GlobalVariable as GlobalVariable

/**
 * This code was copied from the post https://forum.katalon.com/t/method-code-too-large/9475/27
 * 
 */
'Given'
WS.comment("Setup")
def requestUrl = "http://demoaut.katalon.com"
def adminToken = "foo"
jsonTemplate = new File("./Include/resources/logUnique.json").text
bindings = [ 'campaign' : campaign, 'event' : event ]

TemplateEngine engine = new groovy.text.SimpleTemplateEngine()
String reqBody = engine.createTemplate(jsonTemplate).make(bindings).toString()

TestObject request = findTestObject('events/EventsLogUnique', [('url') : requestUrl, ('body') : reqBody, ('token') : GlobalVariable.sysadminToken])
assert request != null

'When'
WS.comment("Sending POST request: ${request.getRestUrl()}")
WS.comment("With body: ${request.getHttpBody()}")
responseBody = WS.sendRequest(request)
println (responseBody)

'Then'
WS.comment("Check response status")
sCode = responseBody.statusCode
println (sCode)
