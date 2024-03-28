
pipeline {
  agent {
    docker { image 'node:21-alpine3.18'
           }
  }
  stages {
    stage('Test') {
      steps {
        sh 'node --version'
      }
    }
  }
}

