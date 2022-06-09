node{
    def app
    stage('Clone repository'){
        checkout scm


    } 
    stage('updateK8S'){
        script{


            catchError(buildResult:"SUCCESS",stageResult:"FAILURE") {
                withCredentials([usernamePassword(credentialsId:'GIT',passwordvariable : 'PWD' ,usernameVariable: 'USERNAME')]){
                  bat 'git config user.email osiephriobukohwo@gmail.com'
                  bat  'git config user.name Osiephri'
                  bat  'cat k8s_manifest.yaml'
                  bat  "sed -i 's+scinet0786/flaskdemo.*+scinet0786/flaskdemo:${manifest}+g' k8s_manifest.yaml"
                  bat  "cat deployment.yaml"
                  bat  "git add ."
                  bat  "git commit -m 'Done by Jenkins Job changemanifest: ${env.BUILD_NUMBER}'"
                  bat "git push https://${USERNAME}:${PWD}@github.com/${USERNAME}}/kubernetesmanifest.git HEAD:main" 
                }
            }            
    }
    }
}
        
        
