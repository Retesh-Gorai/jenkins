pipeline {
  agent {
    docker { image 'maven:latest'
           }
  }
  stages {
    stage('Upload to Artifactory') {
      steps {
        script {
          def uploadArtifactory = load "${WORKSPACE}/src/groovy/uploadArtifactory.groovy"
           uploadArtifactory.execute()
        }
      }
    }
  }
}
