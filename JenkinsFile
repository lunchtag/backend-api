pipeline {

  agent any
  
  stages {
    stage('SonarQube Analysis') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh 'mvn clean verify sonar:sonar'
        }
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
    stage('Test') {
       steps {
          sh 'mvn test'
       }
       post {
         always {
           junit 'target/surefire-reports/*.xml'
         }
       }
    }
  }
}
