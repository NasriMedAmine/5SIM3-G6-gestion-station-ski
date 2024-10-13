pipeline {
    agent any
    
    triggers {
        // Déclenche le build dès qu'un changement est détecté dans le dépôt Git.
        pollSCM('H/1 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                // Récupère le code source depuis le référentiel Git.
                git branch: 'main', url: 'https://github.com/NasriMedAmine/ProjetDevops.git'
            }
        }

        stage('Maven Clean') {
            steps {
                // Exécuter la commande Maven clean
                sh 'mvn clean'
            }
        }


        stage('Maven Clean') {
            steps {
                // Exécuter la commande Maven clean
                sh 'mvn build'
            }
        }




        stage('Maven Install') {
            steps {
                // Exécuter la commande Maven install
                sh 'mvn install'
            }
        }
    }

    post {
        always {
            // Toujours afficher un message de fin.
            echo 'Pipeline terminé.'
        }
    }
}
