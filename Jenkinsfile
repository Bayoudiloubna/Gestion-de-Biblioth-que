pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven' // Maven tool configured in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Bayoudiloubna/Gestion-de-Biblioth-que.git'
            }
        }
        stage('Run Unit Tests') {
            steps {
                // Run unit tests
                sh '${MAVEN_HOME}/bin/mvn test'
            }
        }
    }
    post {
        always {
            // Archive and publish test results
            junit '**/target/surefire-reports/*.xml'

            // Send email with build status
            emailext(
                to: 'bayoudi2404loubna@gmail.com',
                subject: "Jenkins Build: ${currentBuild.currentResult}",
                body: """Build Result: ${currentBuild.currentResult}
                        Build URL: ${env.BUILD_URL}
                        """
            )
        }
    }
}
