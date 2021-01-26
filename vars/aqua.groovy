def call(images) {
    try {
        images.each { item ->
            aquaMicroscanner imageName: item, notCompliesCmd: '', onDisallowed: 'ignore', outputFormat: 'html'
        }
    } catch(Exception e) {
        println('[ERROR] The Aqua Microscanner does not work')
    }
}