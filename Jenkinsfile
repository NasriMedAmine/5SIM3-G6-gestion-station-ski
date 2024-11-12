pipeline {
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git branch: 'SmineMedRached-5sim3-G6',
                    url: 'https://github.com/NasriMedAmine/5SIM3-G6-gestion-station-ski.git'
            }
        }

        stage('Cleaning the Project') {
            steps {
                sh "mvn -B -DskipTests clean"
            }
        }

        stage('Artifact Construction') {
            steps {
                sh "mvn -B -DskipTests package"
            }
        }

        stage('Unit Tests') {
            steps {
                sh "mvn test"
            }
        }

        stage('Code Quality Check via SonarQube') {
            environment {
                // Specify SonarQube credentials if required
                SONAR_AUTH = credentials('rached')
            }
            steps {
                sh "mvn clean verify sonar:sonar -Dsonar.projectKey=skier -Dsonar.host.url=localhost:9000 -Dsonar.login=$SONAR_AUTH"
            }
        }

        stage('Publish to Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image from the Dockerfile
                    sh 'docker build -t rihemchagour-g2 .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Login to Docker Hub and push the Docker image
                    sh 'echo "r11031999" | docker login --username "rihem05" --password-stdin'
                    sh 'docker tag rihemchagour-g2 rihem05/devops:latest'
                    sh 'docker push rihem05/devops:latest'
                }
            }
        }

        stage('Run Spring & MySQL Containers') {
            steps {
                script {
                    // Start containers using Docker Compose
                    sh 'docker compose up -d'
                }
            }
        }
    }
}
