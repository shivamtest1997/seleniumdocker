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
                  bat 'docker build -t=shivamtest1997/selenium_docker_jenkins:v1 .'
            }

        }
        stage("push docker image"){
             environment {
                    // Define Docker Hub credentials
                    DOCKER_HUB_USERNAME = credentials('shivamtest1997')
                    DOCKER_HUB_PASSWORD = credentials('Shivam@123')
                }
              steps{

                script {
                                  // Login to Docker Hub
                                  bat "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"

                                  // Push the Docker image to Docker Hub
                                  bat "docker push ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                              }

//                     bat '${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
//                     bat 'docker push selenium_docker_jenkins'
//                     bat "docker tag shivamtest1997/selenium_docker_jenkins:latest shivamtest1997/selenium_docker_jenkins:${env.BUILD_NUMBER}"
//                     bat "docker push shivamtest1997/selenium_docker_jenkins:${env.BUILD_NUMBER}"

              }

            }
    }

        post{
              always{
                      bat 'docker logout'

              }

        }
}
