pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Project build and Install') {
            steps {
                powershell 'mvn clean install -DskipTests=true'
            }
        }
        stage('Project test') {
            steps {
                powershell 'mvn test -Dsuite=%SUITE% -Dconfig=%CONFIG%'
            }
        }
        stage('Copy logs') {
            steps {
                archiveArtifacts artifacts: 'target/logs/*', followSymlinks: false
            }
        }
    }
    post('Allure results') {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}