def call(images) {
    try {
        imageName = "Aqua-check:${BUILD_NUMBER}"
        docker.build(imageName,'--no-cache -f Dockerfile .')
        aquaMicroscanner imageName: imageName, notCompliesCmd: '', onDisallowed: 'ignore', outputFormat: 'html'
        sh "docker rmi ${imageName}"
    } catch(Exception e) {
        println('[ERROR] The Aqua Microscanner does not work')
    }
}