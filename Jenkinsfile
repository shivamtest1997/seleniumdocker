pipeline
{

    agent any
     environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-creds')
        DOCKER_HUB_USERNAME = sh(script: "echo \$DOCKER_HUB_CREDENTIALS_USR", returnStdout: true).trim()
        DOCKER_HUB_PASSWORD = sh(script: "echo \$DOCKER_HUB_CREDENTIALS_PSW", returnStdout: true).trim()
        IMAGE_NAME = 'shivamtest1997/selenium_docker_jenkins'
    }
    stages{

        stage("build jar file"){
            steps{
                bat 'mvn clean package -DskipTests'
            }

        }
        stage("Build docker image"){
             steps{
                  bat 'docker build -t=shivamtest1997/selenium_docker_jenkins .'
            }

        }
        stage('Push Image'){
            steps {
                      script {
                            docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
                                    docker.image(IMAGE_NAME).push()
                            }
                     }
            }
            }
    }

    post{
            always{
                    bat 'docker logout'

            }

    }
}
