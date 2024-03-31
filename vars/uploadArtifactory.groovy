def execute() {
    def jfrogCliUrl = "https://releases.jfrog.io/artifactory/jfrog-cli/v2/2.54.0/jfrog-cli-linux-amd64/jfrog"
    // def jfrogCliVersion = "2"
    def jfrogCliInstallationDir = "/usr/local/bin"
    def targetJarFilePath = "src/target/testJava.jar"
    def artifactoryUrl = "https://devopsorg.jfrog.io/artifactory/"

    //curl -fL https://getcli.jfrog.io/v2 | sh
    //curl -fL https://install-cli.jfrog.io | sh
    // https://releases.jfrog.io/artifactory/jfrog-cli/v2/2.5.0/jfrog-cli-linux-amd64/
    // Download and install JFrog CLI
    // sh "chmod +wx ${jfrogCliInstallationDir}"
    sh "curl -fL ${jfrogCliUrl} -o ${WORKSPACE}/jfrog"
    sh "echo Jfrog cli has been installed successfully"
    sh "ls -ltR"
    sh "chmod +x ${WORKSPACE}/jfrog"
    sh "echo Jfrog cli permission has been modified successfully"
    // sh "echo $JFROG_CLI_HOME_DIR"
    sh "${WORKSPACE}/jfrog --version"

    // Use Jenkins credentials to get Artifactory api token
    withCredentials([string(credentialsId: 'artifactory-access-token', variable: 'SECRET_TEXT')]) {
        // Use JFrog CLI to upload the JAR file to Artifactory
        sh "echo Jfrog config has been initiated"
        sh 'echo The secret is $SECRET_TEXT'
        sh '${WORKSPACE}/jfrog c add articonfig310124 --url "${artifactoryUrl}" --access-token "${SECRET_TEXT}" --interactive=false --config "${WORKSPACE}"'
        // sh "echo Jfrog config has been completed"
        // sh '${WORKSPACE}/jfrog rt u ${WORKSPACE}/${targetJarFilePath} test-repo/'
        // sh "echo Jfrog upload has been completed"
    }
    // sh "echo Hi from retesh"
}
