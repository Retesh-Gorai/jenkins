pipeline {
  agent {
    docker { image 'jenkins/inbound-agent'
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
