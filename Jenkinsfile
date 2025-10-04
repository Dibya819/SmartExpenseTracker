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
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Dibya819/SmartExpenseTracker.git'
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    retry(2) {
                        sh 'mvn clean package'
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t expense-tracker:latest .'
            }
        }

        stage('Docker Compose Deploy') {
            steps {
                sh 'docker-compose down'
                sh 'docker-compose up -d'
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
