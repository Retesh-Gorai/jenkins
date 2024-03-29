def execute() {
    def jfrogCliUrl = "https://install-cli.jfrog.io"
    def jfrogCliVersion = "2"
    def jfrogCliInstallationDir = "/usr/local/bin"
    def targetJarFilePath = "../target/testJava.jar"
    def artifactoryUrl = "https://jcenter.bintray.com"

    //curl -fL https://getcli.jfrog.io/v2 | sh
    // url -fL https://install-cli.jfrog.io | sh
    // https://releases.jfrog.io/artifactory/jfrog-cli/v2/2.5.0/jfrog-cli-linux-amd64/
    // Download and install JFrog CLI
    // sh "chmod +wx ${jfrogCliInstallationDir}"
    sh "curl -fL ${jfrogCliUrl}"
    sh "echo Jfrog cli has been installed successfully"
    sh "ls -ltR"
    sh "pwd"
    sh "echo ${WORKSPACE}"
    sh "chmod +x ${WORKSPACE}/jf"
    // sh "echo $JFROG_CLI_HOME_DIR"
    sh "${WORKSPACE}/jf --version"
    sh "echo Jfrog cli permission has been modified successfully"

    // Use Jenkins credentials to get Artifactory username and password
    withCredentials([usernamePassword(credentialsId: 'jfrog-artifactory-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        // Use JFrog CLI to upload the JAR file to Artifactory
        sh "echo Jfrog config has been initiated"
        sh "${jfrogCliInstallationDir}/jfrog rt config --url ${artifactoryUrl} --user ${USERNAME} --password ${PASSWORD} --interactive=false"
        sh "echo Jfrog config has been completed"
        sh "${jfrogCliInstallationDir}/jfrog rt u ${targetJarFilePath} ${artifactoryUrl}/testJava.jar"
        sh "echo Jfrog upload has been completed"
    }
    // sh "echo Hi from retesh"
}
