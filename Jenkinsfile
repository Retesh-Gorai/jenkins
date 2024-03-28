pipeline {
  agent {
    docker { image 'maven:latest'
           }
  }
  stages {
    stage('Upload to Artifactory') {
      steps {
        script {
          echo "Workspace: ${WORKSPACE}"
          def uploadArtifactory = load "${WORKSPACE}/src/groovy/uploadArtifactory.groovy"
          if (uploadArtifactory) {
            echo "uploadArtifactory successfully loaded"
            uploadArtifactory.execute()
          } else {
            echo "uploadArtifactory is null"
          }
        }
      }
    }
  }
}
