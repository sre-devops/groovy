def call(image) {
    try {
        aquaMicroscanner imageName: '${image}', notCompliesCmd: '', onDisallowed: 'ignore', outputFormat: 'html'
    } catch(Exception e) {
        println('[ERROR] The Aqua Microscanner does not work')
    }
}