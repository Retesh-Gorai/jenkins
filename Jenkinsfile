@Library("uploadArtifactory") _
pipeline {
  agent {
    docker { image 'maven:latest'
           }
  }
  environment {
        JFROG_CLI_HOME_DIR = '${WORKSPACE}/'
  }
  stages {
    stage('Upload to Artifactory') {
      steps {
        script {
            echo "current ws: ${WORKSPACE}"
            uploadArtifactory.execute()
        }
      }
    }
  }
}
