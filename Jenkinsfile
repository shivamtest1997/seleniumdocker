pipeline
{

    agent any

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
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{

                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat 'docker push shivamtest1997/selenium_docker_jenkins'
            }
        }

    }

    post{
            always{
                    bat 'docker logout'

            }

     }



}
