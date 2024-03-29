def execute() {
    // def jfrogCliUrl = "https://getcli.jfrog.io"
    // def jfrogCliVersion = "1.50.0"
    // def jfrogCliInstallationDir = "/usr/local/bin"
    // def targetJarFilePath = "../target/testJava.jar"
    // def artifactoryUrl = "https://jcenter.bintray.com"

    // // Download and install JFrog CLI
    // sh "curl -fL ${jfrogCliUrl}/artifactory/jfrog-cli/v${jfrogCliVersion}/jfrog-cli-linux-amd64/jfrog -o ${jfrogCliInstallationDir}/jfrog"
    // sh "chmod +x ${jfrogCliInstallationDir}/jfrog"

    // // Use Jenkins credentials to get Artifactory username and password
    // withCredentials([usernamePassword(credentialsId: 'jfrog-artifactory-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
    //     // Use JFrog CLI to upload the JAR file to Artifactory
    //     sh "${jfrogCliInstallationDir}/jfrog rt config --url ${artifactoryUrl} --user ${USERNAME} --password ${PASSWORD} --interactive=false"
    //     sh "${jfrogCliInstallationDir}/jfrog rt u ${targetJarFilePath} ${artifactoryUrl}/testJava.jar"
    // }
    sh "echo Hi from retesh"
}
