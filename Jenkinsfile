pipeline {
    agent any
    triggers {
        // Déclenche le pipeline automatiquement lorsqu'un push est détecté
        pollSCM('* * * * *') // Vérifie chaque minute pour un nouveau commit (modifiable pour éviter des déclenchements trop fréquents)
    }
    stages {
        stage('Récupération du code source') {
            steps {
                // Récupération du code source depuis le référentiel Git
                git 'https://github.com/NasriMedAmine/ProjetDevops.git'
            }
        }
        stage('Afficher la date système') {
            steps {
                script {
                    def date = new Date()
                    echo "Date système : ${date}"
                }
            }
        }
    }
}

