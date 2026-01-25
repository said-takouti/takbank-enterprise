pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'jdk21'
    }

    environment {
        DOCKERHUB_USER = "takouti"
        IMAGE_NAME = "tak-wallet"
        IMAGE_TAG = "v1-${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Build & Test') {
            steps {
                dir('backend/tak-wallet') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                dir('backend/tak-wallet') {
                    script {
                        sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."

                        sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
                        sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKERHUB_USER}/${IMAGE_NAME}:latest"

                        withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {

                            sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"

                            sh "docker push ${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
                            sh "docker push ${DOCKERHUB_USER}/${IMAGE_NAME}:latest"
                        }
                    }
                }
            }
        }
    }

    post {
        always { cleanWs() }
    }
}