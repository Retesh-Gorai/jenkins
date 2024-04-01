def execute() {
    def jfrogCliUrl = "https://releases.jfrog.io/artifactory/jfrog-cli/v2-jf/2.50.0/jfrog-cli-linux-amd64/jf"
    def targetJarFilePath = "src/target/testJava.jar"
    def artifactoryUrl = "https://devopsorg.jfrog.io/"

    sh "curl -fL ${jfrogCliUrl} -o ${WORKSPACE}/jf"
    sh "echo Jfrog cli has been installed successfully"
    sh "ls -ltR"
    sh "chmod +x ${WORKSPACE}/jf"
    sh "echo Jfrog cli permission has been modified successfully"
    sh "${WORKSPACE}/jf --version"

    // Use Jenkins credentials to get Artifactory api token
    // withCredentials([usernamePassword(credentialsId: 'jfrog-jenkins-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
    withCredentials([string(credentialsId: 'artifactory-access-token', variable: 'SECRET_TEXT')]) {
        // Use JFrog CLI to upload the JAR file to Artifactory
        sh 'echo The secret is $SECRET_TEXT'
        sh "\"${WORKSPACE}/jf\" rt u --url \"${artifactoryUrl}\" --flat=true --access-token \"${SECRET_TEXT}\" \"${WORKSPACE}/${targetJarFilePath}\" artifactory/test-repo/"
        sh "echo Jfrog upload has been completed"
    }
}
