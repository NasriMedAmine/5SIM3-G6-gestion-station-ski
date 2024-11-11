pipeline {
    agent any
    
    triggers {
        // Déclenche le build dès qu'un changement est détecté dans le dépôt Git.
        pollSCM('H/1 * * * *')
    }

    environment {
        // Define any global environment variables
        // NEXUS_URL = 'http://localhost:8081'
        SONAR_URL = 'http://localhost:9000'
        // DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        // REPO_NAME = 'example-repo'

        NEXUS_URL = 'http://localhost:8081/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexusRepo'




    DOCKER_HUB_CREDENTIALS = 'DockerHub'
    DOCKER_IMAGE = 'medaminenasri/devops:latest'


        // NEXUS_VERSION = "nexus3"
        // NEXUS_PROTOCOL = "http"
        // NEXUS_URL = "http://192.168.159.129/:8081"
        // NEXUS_REPOSITORY = "deployementRepo"
        // NEXUS_CREDENTIAL_ID = "nexusRepo"

    }
    




    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'MedAmineNasri-5SIM3-G6', url: 'https://github.com/NasriMedAmine/5SIM3-G6-gestion-station-ski.git'
            }
        }

        stage('Maven Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        
        stage('Maven compile') {
            steps {
                sh 'mvn compile'
            }
        }






        stage('Maven test') {
            steps {
                sh 'mvn test'
            }
        }


       
        stage('SonarQube Analysis') {
            environment {
                // Specify SonarQube credentials if required
                SONAR_AUTH = credentials('sonartoken')
            }
            steps {
                // Run SonarQube analysis
                sh 'mvn sonar:sonar -Dsonar.host.url=$SONAR_URL -Dsonar.login=$SONAR_AUTH'
            }
        }
        



        stage('Maven package') {
            steps {
                sh 'mvn package '
            }
        }


        // stage('Deploy to Nexus') {
        //     steps {
        //         withCredentials([usernamePassword(credentialsId: "${NEXUS_CREDENTIALS_ID}", usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
        //             sh 'mvn deploy -X -e -Dnexus.username=admin -Dnexus.password=qsddsq0987QSDDSQ?'
        //         }
        //     }
        // }

        stage('Build the Docker Image') {
            steps {
                sh 'sudo docker build -t 5sim3-g6-gestion-station-ski .'
            }
        }








        stage('Login to Docker Hub') { 
            steps { 
                script {
                     docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                         echo 'Logged in to Docker Hub' 
                        } 
                } 
            } 
        } 
        stage('Push Docker Image') {
             steps {
                 script { 
                    docker.image('5sim3-g6-gestion-station-ski').push('latest') 
                    }
                } 
            }














        stage('Docker Compose') {
            steps {
                sh 'sudo docker-compose up -d'
            }
        }
        


        
    }

    post {
        always {
            echo 'Pipeline terminé.'
        }
    }
}
