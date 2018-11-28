import groovy.
import groovy.json.JsonSlurper

def inputFile = new File("cucumber.json")
def inputJSON = new JsonSlurper().parseText(inputFile.text)

url = "http://localhost:38082"

println("URL!")

//def client = new RESTClient(url)
//
//println("inicio da chamada rest!")
//
//def response = client.post(path: "/rest/cucumberplugin/DLA/1/ccb/reports",
//  contentType: JSON,
//  body: inputJSON,
//  headers: [Accept: 'application/json'])
//
//println("Status: " + response.status)
//if (response.data) {
//  println("Content Type: " + response.contentType)
//  println("Headers: " + response.getAllHeaders())
//  println("Body:\n" + JsonOutput.prettyPrint(JsonOutput.toJson(response.data)))
//}