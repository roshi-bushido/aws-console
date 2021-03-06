grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
   //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    test: false,

//    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()

        mavenRepo "http://central.maven.org/maven2/"
        mavenRepo "http://repo.spring.io/milestone"
        mavenRepo "http://repo1.maven.org/maven2"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
        runtime 'mysql:mysql-connector-java:5.1.29'

        compile 'com.google.code.gson:gson:2.2.2'
        compile 'joda-time:joda-time:2.0'
        compile 'org.apache.directory.api:api-all:1.0.0-M28'
        compile 'org.apache.activemq:activemq-core:5.7.0'

        test "org.grails:grails-datastore-test-support:1.0-grails-2.4"
        test 'org.hamcrest:hamcrest-all:1.3'
        test 'org.codehaus.groovy.modules.http-builder:http-builder:0.6'
        test 'org.spockframework:spock-grails-support:0.7-groovy-2.0'
        test "org.gebish:geb-spock:0.9.0"

        build 'org.apache.httpcomponents:httpcore:4.2'
        build 'org.apache.httpcomponents:httpclient:4.2'
        runtime 'org.apache.httpcomponents:httpcore:4.2'
        runtime 'org.apache.httpcomponents:httpclient:4.2'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55"

        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ":cache:1.1.7"
        compile ":asset-pipeline:1.9.6"
        compile ":jms:1.3"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate4:4.3.5.5" // or ":hibernate:3.6.10.17"
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"
        runtime ":twitter-bootstrap:3.3.1"
        runtime ":aws-sdk:1.9.9.1"

        test ":geb:0.7.2"
        test(":spock:0.7") {
            exclude "spock-grails-support"
        }

        // Uncomment these to enable additional asset-pipeline capabilities
        //compile ":sass-asset-pipeline:1.9.0"
        //compile ":less-asset-pipeline:1.10.0"
        //compile ":coffee-asset-pipeline:1.8.0"
        //compile ":handlebars-asset-pipeline:1.3.0.3"
    }
}

