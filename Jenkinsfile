pipeline {
    agent any

    tools {
        jdk   'Java-21'
        maven 'Maven'
    }

    environment {
        APP_NAME   = 'Simulaciones físicas' // cambia este nombre en cada proyecto
        MAVEN_OPTS = '-Xmx1024m -XX:MaxMetaspaceSize=512m'
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo "Rama:   ${GIT_BRANCH}"
                echo "Commit: ${GIT_COMMIT}"
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Compilar') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Empaquetar') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Build completado con éxito'
        }
        failure {
            echo '❌ Build fallido'
        }
        always {
            cleanWs()
        }
    }
}