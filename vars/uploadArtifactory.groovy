def execute() {
    def jfrogCliUrl = "https://releases.jfrog.io/artifactory/jfrog-cli/v2/2.55.0/jfrog-cli-linux-amd64/jfrog"
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
    withCredentials([usernamePassword(credentialsId: 'jfrog-jenkins-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        // Use JFrog CLI to upload the JAR file to Artifactory
        sh "echo Jfrog config has been initiated"
        sh 'echo The secret is $SECRET_TEXT'
        // sh "chmod 777 /"
        // echo "${JFROG_HOME}"
        sh '${WORKSPACE}/jfrog config rm articonfig'
        sh '${WORKSPACE}/jfrog c add articonfig --url "${artifactoryUrl}" --user "${USERNAME}" --password "${PASSWORD}" --interactive=false'
        //--access-token "${SECRET_TEXT}"
        sh '${WORKSPACE}/jfrog config use articonfig'
        sh '${WORKSPACE}/jfrog config show articonfig'
        sh "echo Jfrog config has been completed"
        // sh '${WORKSPACE}/jfrog rt ping'
        // sh '${WORKSPACE}/jfrog rt u ${WORKSPACE}/${targetJarFilePath} test-repo/'
        // sh '"${WORKSPACE}/jfrog" rt u --url "${artifactoryUrl}" "${WORKSPACE}/${targetJarFilePath}" test-repo/'
        sh "\"${WORKSPACE}/jfrog\" rt u --url \"${artifactoryUrl}\" \"${WORKSPACE}/${targetJarFilePath}\" test-repo/"
        sh "echo Jfrog upload has been completed"
        sh '${WORKSPACE}/jfrog config rm articonfig'
        sh "echo Jfrog articonfig has been removed successfully"
    }
    // sh "echo Hi from retesh"
}
