FROM bellsoft/liberica-openjdk-alpine:17.0.8

# # Install curl jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources ./
#ADD runner.sh  runner.sh

# Start the runner.sh
#ENTRYPOINT sh runner.sh
# Start the java command
ENTRYPOINT       java -cp 'libs/*' \
                -DseleniumGridEnabled=true \
                -DseleniumGridHubHost=${HUBHOST:-hub} \
                -Dbrowser=${BROWSER:-chrome}  \
                org.testng.TestNG \
                -threadcount "${THREAD_COUNT:-1}" \
                test-suites/vendorPortal.xml