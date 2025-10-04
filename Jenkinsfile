pipeline {
    agent any

    environment {
        SPRING_DATASOURCE_URL = 'jdbc:mysql://mysql:3306/expenses'
        SPRING_DATASOURCE_USERNAME = 'expenseuser'
        SPRING_DATASOURCE_PASSWORD = 'expensepassword'
        EMAIL_RECIPIENTS = 'dibyabikashpradhan@gmail.com'
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Build & Test') {
            steps {
                script {
                    retry(2) {
                        bat 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t expense-tracker:latest .'
            }
        }

        stage('Docker Compose Deploy') {
            steps {
                bat 'docker-compose down'
                bat 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            mail to: "${EMAIL_RECIPIENTS}",
                 subject: "Build Failed: ${currentBuild.fullDisplayName}",
                 body: "Something went wrong. Please check Jenkins build logs."
        }
    }
}
