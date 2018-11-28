@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7' )
import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder

def inputFile = new File("cucumber.json")
def inputJSON = new JsonSlurper().parseText(inputFile.text)
def http = new HTTPBuilder()

http.request(POST) {
    uri.path = 'http://localhost:38082'
    body = inputJSON
    requestContentType = ContentType.JSON

    response.success = { resp ->
	println "Success! ${resp.status}"
    }

    response.failure = { resp ->
	println "Request failed with status ${resp.status}"
    }
}