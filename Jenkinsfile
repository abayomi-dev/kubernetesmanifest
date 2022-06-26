pipeline{
    agent any
    environment{
        DOCKERHUB_CREDS = crendential('docker')

    }
    stages{
        stage('clone repo'){
            steps{
                checkout scm
            }
        }

        stage('Build images'){
            steps{
                sh "docker build -t scinet0786/background_api:$BUILD_NUMBER ./pushdockerimage/"
            }
        }

        stage('Docker login'){
            steps{
                sh "echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin"
            }

        }

        stage('docker push'){
            steps{
                sh "docker push scinet0786/background_api:$BUILD_NUMBER"
            }
        }

        stage('Update manifest'){
            steps{
                script{
                    build job: 'update manifest', parameters: [string(name: 'manifest', value: scinet0786/background_api:$BUILD_NUMBER)]
                }
            } 
        }

    }

    post{
        always{
            sh 'docker logout '
        }
    }
}
