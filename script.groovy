def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'podman build -t kunalmamodiya/demo-app:jma-2.0 .'
        sh "echo $PASS | podman login -u $USER --password-stdin"
        sh 'podman push kunalmamodiya/demo-app:jma-2.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this