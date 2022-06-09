node{
    def app
    stage('Clone repository'){
        checkout scm
    }
    stage('build image'){
        app = docker.build(scinet/flaskdemo)
        }

    stage('test image'){
        app.inside{
            bat 'echo "test passed"'
        }
        }
    
    stage('push image'){
        docker.withRegistry("https://registry.hub.docker.com",'docker'){
            app.push ("${env.BUILD_NUMBER}")
        }
        }
    stage('manifest update'){
        echo 'Triggering manifest update'
        build job: "manifest update",parameters[string(name:'manifest' , value: env.BUILD_NUMBER)]
    }
    }
    
