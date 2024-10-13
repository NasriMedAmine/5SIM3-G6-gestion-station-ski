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

        stage('Show System Date') {
            steps {
                // Affiche la date système dans la console de Jenkins.
                script {
                    def currentDate = new Date()
                    echo "Current Date: ${currentDate}"
                }
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
