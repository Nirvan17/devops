def buildJar() {
    echo 'building the application...'
    sh 'mvn package'

}

def buidlImage() {
    echo 'building the application...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        // sh "docker build -t nirvanb/demo-app:${IMAGE_NAME} ."
        sh "docker build -t nirvanb/demo-app:jma-2.0 ."

        sh 'echo $PASS | docker login -u $USER --password-stdin'
        // sh "docker push nirvanb/demo-app:${IMAGE_NAME}"
        sh "docker push nirvanb/demo-app:jma-2.0"
}
def deployApp() {
    echo 'deploying the application...'
    // echo "deploying version ${params.VERSION}"
} 
return this