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
                sh 'echo $DOCKER_HUB_PSW | docker login -u $DOCKER_HUB_USR --password-stdin'
                bat 'docker push shivamtest1997/selenium_docker_jenkins:latest'
                bat "docker tag shivamtest1997/selenium_docker_jenkins:latest shivamtest1997/selenium_docker_jenkins:${env.BUILD_NUMBER}"
                bat "docker push shivamtest1997/selenium_docker_jenkins:${env.BUILD_NUMBER}"
            }
        }

    }

    post{
            always{
                    bat 'docker logout'

            }

    }
}
