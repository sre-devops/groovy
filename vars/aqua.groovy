def call() {
    try {
        projectName = env.JOB_NAME.split('/')[0].toLowerCase()
        imageName = "${projectName}:${BUILD_NUMBER}"
        docker.build(imageName,'--no-cache -f Dockerfile .')
        aquaMicroscanner imageName: imageName, notCompliesCmd: '', onDisallowed: 'ignore', outputFormat: 'html'
        sh "docker rmi ${imageName}"
    } catch(Exception e) {
        println('[ERROR] The Aqua Microscanner does not work')
    }
}
