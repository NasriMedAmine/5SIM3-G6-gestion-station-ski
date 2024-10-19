pipeline {
    agent any
    
    triggers {
        // Déclenche le build dès qu'un changement est détecté dans le dépôt Git.
        pollSCM('H/1 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/NasriMedAmine/ProjetDevops.git'
            }
        }

        stage('Maven Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        // stage('Maven Install') {
        //     steps {
        //         // Exécuter la commande Maven install
        //         sh 'mvn install'
        //     }
        // }
        
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



        stage('Maven package') {
            steps {
                sh 'mvn package'
            }
        }

        // stage('Maven Install') {
        //     steps {
        //         // Exécuter la commande Maven install
        //         sh 'mvn install'
        //     }
        // }

        


        
    }

    post {
        always {
            echo 'Pipeline terminé.'
        }
    }
}
