def execute() {
    def jfrogCliUrl = "https://releases.jfrog.io/artifactory/jfrog-cli/v2-jf/2.50.0/jfrog-cli-linux-amd64/jf"
    // def jfrogCliVersion = "2"
    def jfrogCliInstallationDir = "/usr/local/bin"
    def targetJarFilePath = "src/target/testJava.jar"
    def artifactoryUrl = "https://devopsorg.jfrog.io/"

    //curl -fL https://getcli.jfrog.io/v2 | sh
    //curl -fL https://install-cli.jfrog.io | sh
    // https://releases.jfrog.io/artifactory/jfrog-cli/v2/2.5.0/jfrog-cli-linux-amd64/
    // https://releases.jfrog.io/artifactory/jfrog-cli/v2-jf/2.50.0/jfrog-cli-linux-amd64/
    // Download and install JFrog CLI
    // sh "chmod +wx ${jfrogCliInstallationDir}"
    sh "curl -fL ${jfrogCliUrl} -o ${WORKSPACE}/jf"
    sh "echo Jfrog cli has been installed successfully"
    sh "ls -ltR"
    sh "chmod +x ${WORKSPACE}/jf"
    sh "echo Jfrog cli permission has been modified successfully"
    // sh "echo $JFROG_CLI_HOME_DIR"
    sh "${WORKSPACE}/jf --version"

    // Use Jenkins credentials to get Artifactory api token
    // withCredentials([usernamePassword(credentialsId: 'jfrog-jenkins-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
    withCredentials([string(credentialsId: 'artifactory-access-token', variable: 'SECRET_TEXT')]) {
        // Use JFrog CLI to upload the JAR file to Artifactory
        sh "echo Jfrog config has been initiated"
        sh 'echo The secret is $SECRET_TEXT'
        // sh "chmod 777 /"
        // echo "${JFROG_HOME}"
        sh '${WORKSPACE}/jf config rm articonfig'
        // sh "\"${WORKSPACE}/jf\" c add articonfig --url \"${artifactoryUrl}\" --access-token \"${SECRET_TEXT}\" --interactive=false"
        //--access-token "${SECRET_TEXT}"
        // sh '${WORKSPACE}/jf config use articonfig'
        // sh '${WORKSPACE}/jf config show articonfig'
        // sh "echo Jfrog config has been completed"
        // sh '${WORKSPACE}/jfrog rt ping'
        // sh '${WORKSPACE}/jfrog rt u ${WORKSPACE}/${targetJarFilePath} test-repo/'
        // sh '"${WORKSPACE}/jfrog" rt u --url "${artifactoryUrl}" "${WORKSPACE}/${targetJarFilePath}" test-repo/'
        sh "\"${WORKSPACE}/jf\" rt u --url \"${artifactoryUrl}\" --flat=true --access-token \"${SECRET_TEXT}\" \"${WORKSPACE}/${targetJarFilePath}\" artifactory/test-repo/"
        sh "echo Jfrog upload has been completed"
        sh '${WORKSPACE}/jf config rm articonfig'
        sh "echo Jfrog articonfig has been removed successfully"
    }
    // sh "echo Hi from retesh"
}
