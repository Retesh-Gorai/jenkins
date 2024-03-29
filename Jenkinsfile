@Library("uploadArtifactory") _
pipeline {
  agent {
    docker { image 'maven:latest'
           }
  }
  stages {
    stage('Upload to Artifactory') {
      steps {
            uploadArtifactory.execute()
      }
    }
  }
}
