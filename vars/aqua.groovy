def call() {
    try {
        docker.build("aqua-check:${BUILD_NUMBER}",'--no-cache -f Dockerfile .')
        aquaMicroscanner imageName: item, notCompliesCmd: '', onDisallowed: 'ignore', outputFormat: 'html'
        sh "docker rmi aqua-check:${BUILD_NUMBER}"
    } catch(Exception e) {
        println('[ERROR] The Aqua Microscanner does not work')
    }
}